package com.vishal.payment_service.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vishal.payment_service.client.UserServiceClient;
import com.vishal.payment_service.exception.PaymentOrderNotFoundException;
import com.vishal.payment_service.feignclient.AuthUtil;
import com.vishal.payment_service.model.PaymentMethod;
import com.vishal.payment_service.model.PaymentOrder;
import com.vishal.payment_service.payload.dto.BookingDto;
import com.vishal.payment_service.payload.dto.UserDto;
import com.vishal.payment_service.payload.response.PaymentLinkResponse;
import com.vishal.payment_service.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDto booking,
                                                                 @RequestParam PaymentMethod paymentMethod) throws StripeException, RazorpayException {
//        UserDto user=new UserDto(UUID.randomUUID(), "Vishal","vishl@gmail.com");

        UserDto loggedInUser = authUtil.getCurrentUser();
        UserDto user = userServiceClient.findUserByEmail(loggedInUser.email());
        PaymentLinkResponse res=paymentService.createOrder(user, booking, paymentMethod);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentId) throws StripeException, RazorpayException, PaymentOrderNotFoundException {
        PaymentOrder paymentOrderById = paymentService.getPaymentOrderById(paymentId);
        return new ResponseEntity<>(paymentOrderById, HttpStatus.OK);
    }

    @PatchMapping("/proceed")

    public ResponseEntity<Boolean> proceedPayment(@RequestParam String paymentId,
                                                       @RequestParam String paymentLinkId) throws RazorpayException {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentId);

        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
