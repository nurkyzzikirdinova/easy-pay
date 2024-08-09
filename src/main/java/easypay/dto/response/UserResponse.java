package easypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import zikirdinova.easypay.enums.Gender;
import zikirdinova.easypay.enums.Role;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Role role;
    private Gender gender;

}
