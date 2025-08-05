package com.vishal.payment_service.service.impl;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.vishal.payment_service.exception.PaymentOrderNotFoundException;
import com.vishal.payment_service.model.PaymentMethod;
import com.vishal.payment_service.model.PaymentOrder;
import com.vishal.payment_service.model.PaymentOrderStatus;
import com.vishal.payment_service.payload.dto.BookingDto;
import com.vishal.payment_service.payload.dto.UserDto;
import com.vishal.payment_service.payload.response.PaymentLinkResponse;
import com.vishal.payment_service.repo.IPaymentOrderRepo;
import com.vishal.payment_service.service.IPaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentOrderRepo paymentOrderRepo;

    @Value("${stripe.api.key}")
    private String stripSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpaySecretKey;

    @Override
    public PaymentLinkResponse createOrder(UserDto user, BookingDto booking, PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Long amount = (long) booking.getTotal();

        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(booking.getBookingId());
        order.setSalonId(booking.getSalonId());
        PaymentOrder savedOrder = paymentOrderRepo.save(order);

        PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink payment = createRazorpayPaymentLink(user, savedOrder.getAmount(), savedOrder.getPaymentId());

            String paymentUrl = payment.get("short_url");
            String paymentUrlId=payment.get("id");

            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);

            savedOrder.setPaymentLink(paymentUrlId);
            paymentOrderRepo.save(savedOrder);
        }else {
            String paymentUrl=createStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getPaymentId());

            paymentLinkResponse.setPayment_link_url(paymentUrl);
        }

        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long paymentId) throws PaymentOrderNotFoundException {
        Optional<PaymentOrder> paymentOrder = paymentOrderRepo.findById(paymentId);
        if (paymentOrder.isEmpty()) {
            throw new PaymentOrderNotFoundException("Payment order not found");
        }
        return paymentOrder.get();
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return paymentOrderRepo.findByPaymentLink(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDto user, Long Amount, Long orderId) throws RazorpayException {
        Long amount = Amount*100;
        RazorpayClient razorpay=new RazorpayClient(razorpayApiKey, razorpaySecretKey);
        JSONObject paymentLinkRequest=new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");

        JSONObject customer=new JSONObject();
        customer.put("name",user.fullName());
        customer.put("email",user.email());
        paymentLinkRequest.put("customer",customer);

        JSONObject notify=new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("reminder", true);
        paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success"+orderId);
        paymentLinkRequest.put("callback_method","get");

        return razorpay.paymentLink.create(paymentLinkRequest);
    }

    @Override
    public String createStripePaymentLink(UserDto user, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey=stripSecretKey;

            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/payment-success"+orderId)
                    .setCancelUrl("http://localhost:3000/payment/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd") // or your currency
                                                    .setUnitAmount(amount*100) // amount in cents
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Salon Booking #" + orderId)
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            Session session = Session.create(params);
            return session.getUrl(); // return the payment link
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)){
                RazorpayClient razorpay=new RazorpayClient(razorpayApiKey, razorpaySecretKey);

                Payment payment=razorpay.payments.fetch(paymentId);
                Integer amount=payment.get("amount");
                String status=payment.get("status");

                if (status.equals("captured")){
//                    produce kafka event
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentOrderRepo.save(paymentOrder);
                    return true;
                }
                return false;
            }else {
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepo.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
