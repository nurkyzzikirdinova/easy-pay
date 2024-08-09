package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.entities.User;

import java.time.LocalDateTime;

@Data
public class FineRequest {
    private Double amount;
    private LocalDateTime issuedDate;
    private LocalDateTime dueDate;
    private User user;
    private Boolean paid;
}
