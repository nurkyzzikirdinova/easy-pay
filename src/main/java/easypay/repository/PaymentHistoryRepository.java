package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zikirdinova.easypay.entities.PaymentHistory;
import zikirdinova.easypay.enums.PaymentStatus;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    List<PaymentHistory> findAllByPaymentStatus(PaymentStatus paymentStatus);

}
