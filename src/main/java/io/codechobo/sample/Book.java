package io.codechobo.sample;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    protected Book(String title) {
        this.title = title;
    }
}

interface BookRepository extends JpaRepository<Book, Long>{}
