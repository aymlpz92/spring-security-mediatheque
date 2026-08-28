package fr.simplon.springsecuritymediatheque.model.mappers;

import org.springframework.stereotype.Component;

import fr.simplon.springsecuritymediatheque.model.dtos.books.BookDTO;
import fr.simplon.springsecuritymediatheque.model.entity.Book;

@Component
public class BookMapper {

    public static BookDTO bookToDto(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .categories(book.getCategories())
                .publicationDate(book.getPublicationDate())
                .stock(book.getStock())
                .build();
    }

    public static Book dtoToEntity(BookDTO dto) {
        return Book.builder()
                .title(dto.title())
                .author(dto.author())
                .categories(dto.categories())
                .publicationDate(dto.publicationDate())
                .stock(dto.stock())
                .build();
    }

}
