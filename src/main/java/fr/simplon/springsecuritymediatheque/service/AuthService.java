package fr.simplon.springsecuritymediatheque.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.simplon.springsecuritymediatheque.model.dtos.users.LoginRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.LoginResponse;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterResponse;
import fr.simplon.springsecuritymediatheque.model.entity.User;
import fr.simplon.springsecuritymediatheque.model.exceptions.EmailAlreadyExists;
import fr.simplon.springsecuritymediatheque.model.mappers.UserMapper;
import fr.simplon.springsecuritymediatheque.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

//    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public RegisterResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExists("Cet email est déjà utilisé");
        }
        User user = UserMapper.registerRequestToUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return UserMapper.userToRegisterResponse(user);
    }

    public LoginResponse loginUser(LoginRequest request) {
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        String token = tokenService.generateToken(auth);
        return new LoginResponse(request.email(), token);
    }

}
