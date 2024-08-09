package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.enums.TransactionStatus;

import java.time.LocalDateTime;
@Data
public class TransactionRequest {
    private String transactionReference;
    private LocalDateTime transactionDate;
    private Double amount;
    private Long paymentId;
    private TransactionStatus transactionStatus;

}
