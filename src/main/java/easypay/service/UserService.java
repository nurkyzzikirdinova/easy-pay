package easypay.service;

import zikirdinova.easypay.dto.request.SignInRequest;
import zikirdinova.easypay.dto.request.UserRequest;
import zikirdinova.easypay.dto.response.AuthenticationResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    SimpleResponse signUp(UserRequest userRequest);
   AuthenticationResponse signIn(SignInRequest signInRequest);
    SimpleResponse updateUser(Long userId, UserRequest userRequest);
    SimpleResponse deleteUser(Long userId);
    UserResponse getUserById(Long userId);
    List<UserResponse> getAllUsers();
}
