package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.enums.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private String paymentReference;
    private LocalDateTime paymentDate;
    private Double amount;
    private PaymentStatus paymentStatus;
}