package fr.simplon.springsecuritymediatheque.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id).isEmpty() ? bookRepository.findById(id) : Optional.empty();
    }

    public Optional<Book> updateBook(String id, Book updateBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book currentBook = optionalBook.get();
            currentBook.setAuthor(updateBook.getAuthor());
            currentBook.setTitle(updateBook.getTitle());
            currentBook.setBookCategories(updateBook.getBookCategories());
            currentBook.setPublicationDate(updateBook.getPublicationDate());
            currentBook.setStock(updateBook.getStock());
            return Optional.of(currentBook);
        }
        return Optional.empty();
    }

    public void deleteBook(String id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        }
    }
}
