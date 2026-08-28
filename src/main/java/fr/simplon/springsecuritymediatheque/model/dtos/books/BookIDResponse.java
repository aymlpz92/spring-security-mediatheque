package fr.simplon.springsecuritymediatheque.model.dtos.books;

import java.util.UUID;

public record BookIDResponse(String title, UUID id) {
}
