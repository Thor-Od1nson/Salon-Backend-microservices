package com.vishal.payment_service.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vishal.payment_service.exception.PaymentOrderNotFoundException;
import com.vishal.payment_service.model.PaymentMethod;
import com.vishal.payment_service.model.PaymentOrder;
import com.vishal.payment_service.payload.dto.BookingDto;
import com.vishal.payment_service.payload.dto.UserDto;
import com.vishal.payment_service.payload.response.PaymentLinkResponse;

import java.util.UUID;

public interface IPaymentService {

    PaymentLinkResponse createOrder(UserDto user, BookingDto bookingDto, PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long paymentId) throws PaymentOrderNotFoundException;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDto user,
                                          Long amount,
                                          Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDto user,
                                          Long amount,
                                          Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;


}
