package com.project.fitness.Security;

import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username )throws UsernameNotFoundException{


        // finding the user details from the user(project) and setting into the spring security default user

        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException(" user not found "+username);
        }
        return org.springframework.security.core.userdetails.User.
        builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("user not found with id " + id);
        }
        return org.springframework.security.core.userdetails.User.
                builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();
    }


}
