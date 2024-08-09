package easypay.dto.request;

import lombok.Data;
import zikirdinova.easypay.entities.User;

import java.time.LocalDateTime;

@Data
public class  QueueRequest {
    private LocalDateTime queueDate;
    private User user;
}