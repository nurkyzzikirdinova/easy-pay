package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.enums.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentHistoryRequest {
    private Payment payment;
    private LocalDateTime timestamp;
    private PaymentStatus paymentStatus;
    private Double amount;
    private User user;
}
