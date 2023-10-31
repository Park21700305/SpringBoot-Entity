package pard.seminar5th.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import pard.seminar5th.entity.book.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
