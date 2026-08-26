package fr.simplon.springsecuritymediatheque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.springsecuritymediatheque.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
