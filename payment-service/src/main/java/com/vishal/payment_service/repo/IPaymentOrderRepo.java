package com.vishal.payment_service.repo;

import com.vishal.payment_service.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPaymentOrderRepo extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLink(String paymentLinkId);
}
