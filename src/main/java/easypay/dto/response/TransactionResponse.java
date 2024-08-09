package easypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zikirdinova.easypay.enums.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionResponse {
    private Long id;
    private String transactionReference;
    private LocalDateTime transactionDate;
    private Double amount;
    private TransactionStatus transactionStatus;

}
