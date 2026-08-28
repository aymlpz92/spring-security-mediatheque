package fr.simplon.springsecuritymediatheque.model.dtos.users;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;

@Builder
public record RegisterResponse(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
}
