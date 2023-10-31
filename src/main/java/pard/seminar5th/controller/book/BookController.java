package pard.seminar5th.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pard.seminar5th.dto.book.request.BookBorrowRequest;
import pard.seminar5th.dto.book.request.BookCreateRequest;
import pard.seminar5th.dto.book.request.BookReturnRequest;
import pard.seminar5th.dto.book.response.BookResponse;
import pard.seminar5th.entity.book.Book;
import pard.seminar5th.service.book.BookService;

import java.util.List;

/*
    API(Application Programming Interface)는 서로 다른 프로그램 간에 원하는
    데이터를 주고받기 위해 “어떠한 내용을, 어디에, 어떻게 넣어야 하는지"를 정해 놓은 규칙이자, 데이터 중개자
*/
@RequiredArgsConstructor // 이건 뭐징?
@RestController
@RequestMapping("/api") // 공통되는 URL 묶기. 왜 다들 /api 붙이는지?
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody BookCreateRequest request) {
        Book createdBook = bookService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdBook);
    }

    // 책 빌리는 API
    @PutMapping("/borrow/{bookId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @RequestBody BookBorrowRequest request) {
        Book borrowedBook = bookService.borrowBook(bookId, request.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowedBook);
    }


    // 책 반납하는 API
    @PatchMapping("/return")
    public ResponseEntity<Book> returnBook(@RequestBody BookReturnRequest request) {
        Book returnedBook = bookService.returnBook(request.getTitle());
        return ResponseEntity.status(HttpStatus.OK).body(returnedBook);
    }

    // 책 리스트 보여주는 API
    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<BookResponse> books = bookService.findAll()
                .stream()
                .map(BookResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(books);
    }

    @GetMapping("/book")
    public ResponseEntity<BookResponse> findBookByTitle(@RequestParam String title) {
        Book book = bookService.findByTitle(title);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BookResponse(book));
    }

    // 책 삭제하는 API
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.delete(bookId);

        return ResponseEntity.ok()
                .build();
    }
}

