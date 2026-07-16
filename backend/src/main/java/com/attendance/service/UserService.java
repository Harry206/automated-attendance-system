package com.attendance.service;

import com.attendance.dto.CreateUserRequest;
import com.attendance.dto.UserResponse;
import com.attendance.entity.User;
import com.attendance.enums.Role;
import com.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Used internally by other services
    public User createUser(String email, String password, Role role) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .enabled(true)
                .build();

        return userRepository.save(user);
    }

    // Used by the User API
    public UserResponse createUser(CreateUserRequest request) {

        User savedUser = createUser(
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}