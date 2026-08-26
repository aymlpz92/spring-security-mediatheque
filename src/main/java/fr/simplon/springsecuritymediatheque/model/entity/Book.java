package fr.simplon.springsecuritymediatheque.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import fr.simplon.springsecuritymediatheque.model.BookCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="book")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private List<BookCategory> bookCategories;

    @Column(nullable = false)
    private Date publicationDate;

    @Column(nullable = false)
    private Integer stock;

}
