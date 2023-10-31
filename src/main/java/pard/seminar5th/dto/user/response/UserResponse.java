package pard.seminar5th.dto.user.response;

import lombok.AllArgsConstructor;
import pard.seminar5th.dto.book.response.BookResponse;
import pard.seminar5th.dto.book.response.BookSimpleResponse;
import pard.seminar5th.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private List<BookSimpleResponse> borrowedBooks;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();

        if (user.getBorrowedBooks() != null) {
            this.borrowedBooks = user.getBorrowedBooks().stream()
                    .map(BookSimpleResponse::new)
                    .collect(Collectors.toList());
        }
    }
}

