package easypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import zikirdinova.easypay.enums.Gender;
import zikirdinova.easypay.enums.Role;
import zikirdinova.easypay.validation.PasswordValidation;
import zikirdinova.easypay.validation.UniqueEmail;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    @UniqueEmail
    private String email;
    @PasswordValidation
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Role role;
    private Gender gender;


}
