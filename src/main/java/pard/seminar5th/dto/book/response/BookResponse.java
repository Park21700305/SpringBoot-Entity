package pard.seminar5th.dto.book.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.seminar5th.dto.user.response.UserResponse;
import pard.seminar5th.entity.book.Book;
import pard.seminar5th.entity.user.User;

@NoArgsConstructor
@Getter
public class BookResponse {
    private Long id;
    private String title;
    private UserResponse user;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        if (book.getUser() != null) {
            this.user = new UserResponse(book.getUser());
        }
    }
}

