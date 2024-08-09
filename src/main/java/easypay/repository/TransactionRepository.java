package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zikirdinova.easypay.dto.response.TransactionResponse;
import zikirdinova.easypay.entities.Transaction;
import zikirdinova.easypay.enums.TransactionStatus;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<TransactionResponse> findAllByTransactionStatus(TransactionStatus status);

}
