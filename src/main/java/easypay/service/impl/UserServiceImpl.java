package easypay.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.config.jwt.JwtService;
import zikirdinova.easypay.dto.request.SignInRequest;
import zikirdinova.easypay.dto.request.UserRequest;
import zikirdinova.easypay.dto.response.AuthenticationResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.UserResponse;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.enums.Role;
import zikirdinova.easypay.repository.UserRepository;
import zikirdinova.easypay.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostConstruct
    public void initSaveAdmin() {
        User user = new User();
        user.setUsername("Admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setRole(Role.ADMIN);
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }


    @Override
    public SimpleResponse signUp(UserRequest userRequest) {
        if (userRepository.getUserByEmail(userRequest.getEmail()).isPresent()) {
            return new SimpleResponse(HttpStatus.CONFLICT, "Email already in use");
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User newUser = new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                encodedPassword,
                userRequest.getPhoneNumber(),
                userRequest.getDateOfBirth(),
                userRequest.getRole(),
                userRequest.getGender()
        );

        userRepository.save(newUser);
        return new SimpleResponse(HttpStatus.OK, "User with email " + userRequest.getEmail() + " registered successfully");
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        Optional<User> userOptional = userRepository.getUserByEmail(signInRequest.getEmail());

        if (userOptional.isEmpty()) {
            return buildAuthenticationResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password", null, null);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            return buildAuthenticationResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password", null, null);
        }

        String token = jwtService.generateToken(user);

        return buildAuthenticationResponse(HttpStatus.OK, "Login successful", token, user.getRole());
    }

    private AuthenticationResponse buildAuthenticationResponse(HttpStatus status, String message, String token, Role role) {
        return AuthenticationResponse.builder()
                .httpStatus(status)
                .message(message)
                .token(token)
                .role(role)
                .build();
    }


    @Override
    public SimpleResponse updateUser(Long userId, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return new SimpleResponse(HttpStatus.NOT_FOUND, "User with id " + userId + " not found!");
        }

        User user = userOptional.get();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setRole(userRequest.getRole());
        user.setGender(userRequest.getGender());

        userRepository.save(user);
        return new SimpleResponse(HttpStatus.OK, "User with id " + userId + " updated successfully!");
    }

    @Override
    public SimpleResponse deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return new SimpleResponse(HttpStatus.NOT_FOUND, "User with id " + userId + " not found!");
        }
        userRepository.deleteById(userId);
        return new SimpleResponse(HttpStatus.OK, "User with id " + userId + " deleted successfully!");
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found!");
        }

        User user = userOptional.get();
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getGender()
        );
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getDateOfBirth(),
                        user.getRole(),
                        user.getGender()
                ))
                .collect(Collectors.toList());
    }
}
