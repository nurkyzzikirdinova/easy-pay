package easypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import zikirdinova.easypay.validation.PasswordValidation;
import zikirdinova.easypay.validation.UniqueEmail;

@Data
@AllArgsConstructor
public class SignInRequest {
    @UniqueEmail
    private String email;
    @PasswordValidation
    private String password;

}
