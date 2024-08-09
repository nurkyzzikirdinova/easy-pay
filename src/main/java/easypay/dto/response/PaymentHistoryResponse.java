package easypay.dto.response;

import lombok.Data;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.enums.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentHistoryResponse {
    private Long id;
    private Payment payment;
    private LocalDateTime timestamp;
    private PaymentStatus paymentStatus;
    private Double amount;
    private User user;
}
