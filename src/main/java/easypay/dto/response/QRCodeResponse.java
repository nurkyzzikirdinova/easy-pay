package easypay.dto.response;

import lombok.*;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.entities.User;

import java.time.LocalDateTime;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeResponse {
    private Long id;
    private String code;
    private LocalDateTime generatedDate;
    private User user;
    private Payment payment;
}
