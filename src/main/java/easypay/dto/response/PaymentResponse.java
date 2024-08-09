package easypay.dto.response;

import lombok.Data;
import zikirdinova.easypay.enums.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private Long id;
    private String paymentReference;
    private LocalDateTime paymentDate;
    private Double amount;
    private PaymentStatus paymentStatus;
}