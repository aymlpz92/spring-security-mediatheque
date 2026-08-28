package fr.simplon.springsecuritymediatheque.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.springsecuritymediatheque.model.dtos.books.BookDTO;
import fr.simplon.springsecuritymediatheque.model.dtos.books.BookIDResponse;
import fr.simplon.springsecuritymediatheque.model.entity.Book;
import fr.simplon.springsecuritymediatheque.model.exceptions.BookNotFoundException;
import fr.simplon.springsecuritymediatheque.model.mappers.BookMapper;
import fr.simplon.springsecuritymediatheque.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookIDResponse createBook(BookDTO bookDto) {
        Book book = BookMapper.dtoToEntity(bookDto);
        bookRepository.save(book);
        return new BookIDResponse(book.getTitle(), book.getId());
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::bookToDto)
                .toList();
    }

    public BookDTO getBookById(UUID id) {
        return BookMapper.bookToDto(
                bookRepository.findById(id)
                        .orElseThrow(() -> new BookNotFoundException("Ce livre n'existe pas"))
        );
    }

    public BookDTO getBookByTitle(String title) {
        return BookMapper.bookToDto(
                bookRepository.findByTitle(title)
                        .orElseThrow(() -> new BookNotFoundException("Ce livre n'xiste pas"))
        );
    }

    @Transactional
    public BookIDResponse updateBook(UUID id, BookDTO updateBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = Book.builder()
                    .title(updateBook.title())
                    .author(updateBook.author())
                    .publicationDate(updateBook.publicationDate())
                    .categories(updateBook.categories())
                    .stock(updateBook.stock())
                    .build();
            return new BookIDResponse(book.getTitle(), id);

        }
        throw new BookNotFoundException("Ce livre n'existe pas");
    }

    public void deleteBook(UUID id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Ce livre n'existe pas");
        }

    }
}
