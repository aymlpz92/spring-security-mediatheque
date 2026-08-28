package fr.simplon.springsecuritymediatheque.model.mappers;

import org.springframework.stereotype.Component;

import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterRequest;
import fr.simplon.springsecuritymediatheque.model.dtos.users.RegisterResponse;
import fr.simplon.springsecuritymediatheque.model.entity.User;

@Component
public class UserMapper {

    public static User registerRequestToUser(RegisterRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .authorities(request.authorities())
                .build();
    }

    public static RegisterResponse userToRegisterResponse(User user) {
        return RegisterResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }
}
