package fr.simplon.springsecuritymediatheque.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import fr.simplon.springsecuritymediatheque.model.entity.Book;
import fr.simplon.springsecuritymediatheque.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id).isEmpty() ? Optional.empty() : bookRepository.findById(id);
    }

    public Optional<Book> updateBook(UUID id, Book updateBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return Optional.of(Book.builder()
                    .title(updateBook.getTitle())
                    .author(updateBook.getAuthor())
                    .publicationDate(updateBook.getPublicationDate())
                    .bookCategories(updateBook.getBookCategories())
                    .stock(updateBook.getStock())
                    .build());
        }
        return Optional.empty();
    }

    public void deleteBook(UUID id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        }
    }
}
