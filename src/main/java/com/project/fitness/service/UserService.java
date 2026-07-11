package com.project.fitness.service;


import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;

import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse register(RegisterRequest  request) {
            UserRole role = request.getRole() != null ? request.getRole() : UserRole.USER;
        User user =  User.builder()
                .email(request.getEmail())
                .password( passwordEncoder.encode((request.getPassword()) ))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
        .build();





//                null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName(),
//                Instant.parse("2026-06-30T17:30:00Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                Instant.parse("2026-06-30T17:30:00Z")
//                        .atZone(ZoneOffset.UTC)
//                        .toLocalDateTime(),
//                List.of(),
//                List.of()





        User savedUser =   userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return response;
    }


    public User authenticate(LoginRequest loginRequest) {
        User user  = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null)
            throw new UsernameNotFoundException("Invalid email or password") ;

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

           throw new BadCredentialsException("Invalid credentials");
        }
        return user;
    }
}
