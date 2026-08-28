package fr.simplon.springsecuritymediatheque.model.dtos.books;

import java.time.LocalDate;
import java.util.Set;

import fr.simplon.springsecuritymediatheque.model.BookCategory;
import lombok.Builder;

@Builder
public record BookDTO(String title, String author, Set<BookCategory> categories, LocalDate publicationDate, Integer stock) {
}
