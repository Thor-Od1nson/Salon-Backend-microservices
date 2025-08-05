package com.vishal.payment_service.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long amount;
    private PaymentOrderStatus status=PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID bookingId;

    @Column(nullable = false)
    private UUID salonId;

    private String paymentLink;

    public PaymentOrder() {
    }

    public PaymentOrder(Long paymentId, Long amount, PaymentOrderStatus status, PaymentMethod paymentMethod, UUID userId, UUID bookingId, UUID salonId, String paymentLink) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
        this.bookingId = bookingId;
        this.salonId = salonId;
        this.paymentLink = paymentLink;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentOrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getSalonId() {
        return salonId;
    }

    public void setSalonId(UUID salonId) {
        this.salonId = salonId;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
}
