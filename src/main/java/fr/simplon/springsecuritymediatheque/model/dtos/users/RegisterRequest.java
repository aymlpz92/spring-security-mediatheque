package fr.simplon.springsecuritymediatheque.model.dtos.users;

import java.util.Set;

import fr.simplon.springsecuritymediatheque.model.entity.RoleType;
import lombok.Builder;

@Builder
public record RegisterRequest(String username, String email, String password, Set<RoleType> authorities) {
}
