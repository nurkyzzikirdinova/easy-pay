package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.SignInRequest;
import zikirdinova.easypay.dto.request.UserRequest;
import zikirdinova.easypay.dto.response.AuthenticationResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.UserResponse;
import zikirdinova.easypay.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "User-api")
public class UserApi {
    private final UserService userService;

    @PostMapping("/signUp")
    public SimpleResponse signUp(@RequestBody @Valid UserRequest userRequest) {
        return userService.signUp(userRequest);
    }

    @PostMapping("/singIn")
    public AuthenticationResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }

    @PutMapping("/updateUser/{userId}")
    public SimpleResponse updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }


    @DeleteMapping("/deleteUser/{userId}")
    public SimpleResponse deleteUser(@PathVariable Long userId){
        return  userService.deleteUser(userId);
    }

    @GetMapping("/getAllUsers")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{userId}")
    public UserResponse getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
