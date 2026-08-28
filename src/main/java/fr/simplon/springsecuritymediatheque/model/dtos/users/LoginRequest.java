package fr.simplon.springsecuritymediatheque.model.dtos.users;

import lombok.Builder;

@Builder
public record LoginRequest(String email, String password) {
}
