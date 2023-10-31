package pard.seminar5th.service.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.seminar5th.dto.book.request.BookCreateRequest;
import pard.seminar5th.entity.book.Book;
import pard.seminar5th.entity.user.User;
import pard.seminar5th.repository.book.BookRepository;
import pard.seminar5th.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Book save(BookCreateRequest request) {
        return bookRepository.save(request.toEntity());
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));

        if (book.isBorrowed()) {
            throw new IllegalStateException("이미 빌려진 책입니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        book.setUser(user);
        user.getBorrowedBooks().add(book);

        return bookRepository.save(book);
    }

    public Book returnBook(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 책이 존재하지 않습니다."));

        // 책이 이미 빌려진 상태인지 확인
        if (book.getUser() == null) {
            throw new IllegalStateException("이 책은 빌려진 상태가 아닙니다.");
        }

        // 책을 빌린 사용자 정보를 가져온다!!!!
        User user = book.getUser();

        // 사용자의 빌린 책 목록에서 이 책을 제거 이 부분 중요
        user.getBorrowedBooks().remove(book);

        // 책의 사용자 정보를 null로 설정하여 빌려진 상태가 아님을 표시합니다.
        // 하지만, 이전에 빌렸던 사용자의 정보는 유지됩니다.
        book.setUser(null);

        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 책이 존재하지 않습니다."));
    }

    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}


