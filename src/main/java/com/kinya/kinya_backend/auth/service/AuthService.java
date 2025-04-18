package com.kinya.kinya_backend.auth.service;

import com.kinya.kinya_backend.exceptions.UserAlreadyExistsException;
import com.kinya.kinya_backend.security.TokenProvider;
import com.kinya.kinya_backend.auth.dtos.SignInDto;
import com.kinya.kinya_backend.auth.dtos.SignUpDto;
import com.kinya.kinya_backend.user.User;
import com.kinya.kinya_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @Transactional
    public void signUp(SignUpDto data) throws UserAlreadyExistsException {
        if (repository.findByEmail(data.email()) != null) {
            throw new UserAlreadyExistsException("User with email " + data.email() + " already exists");
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
        SecurityContextHolder.getContext().setAuthentication(authUser);
        System.out.println(authUser);

        return tokenService.generateAccessToken((User) authUser.getPrincipal());

    }

}
