package easypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import zikirdinova.easypay.enums.Role;

@Builder
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String message;
    private String token;
    private HttpStatus httpStatus;
    private Role role;

}
