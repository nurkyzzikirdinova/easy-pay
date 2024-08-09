package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.enums.PaymentStatus;

import java.util.List;

@Repository
public interface PaymentRepository extends  JpaRepository<Payment, Long> {
  List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);
}
