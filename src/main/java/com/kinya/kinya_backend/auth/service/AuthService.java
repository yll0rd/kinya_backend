package com.kinya.kinya_backend.auth.service;

import com.kinya.kinya_backend.auth.TokenProvider;
import com.kinya.kinya_backend.auth.dtos.SignInDto;
import com.kinya.kinya_backend.auth.dtos.SignUpDto;
//import com.kinya.kinya_backend.config.AuthConfig;
import com.kinya.kinya_backend.user.User;
import com.kinya.kinya_backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
  
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenService;

//    @Autowired
//    private AuthConfig authConfig;

    public UserDetails loadUserByUsername(String email) {
        return repository.findByEmail(email);
    }

//    @Transactional
    public void signUp(SignUpDto data) throws IllegalStateException {
        if (repository.findByEmail(data.email()) != null) {
            throw new IllegalStateException("Username already exists");
        }
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = data.role().isPresent() ? new User(data.name(), data.email(), encryptedPassword, data.role().get()) : new User(data.name(), data.email(), encryptedPassword);
        repository.save(newUser);
    }

    public String login(SignInDto input) {

        System.out.println(input);

        Authentication authUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );
        System.out.println(authUser);

        return tokenService.generateAccessToken((User) authUser.getPrincipal());

    }

}
