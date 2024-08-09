package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.entities.User;

import java.time.LocalDateTime;

@Data
public class QRCodeRequest {
    private String code;
    private LocalDateTime generatedDate;
    private User user;
    private Payment payment;
}
