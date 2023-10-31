package pard.seminar5th.dto.book.response;

import lombok.Data;
import pard.seminar5th.entity.book.Book;

@Data
public class BookSimpleResponse {
    private Long id;
    private String title;

    public BookSimpleResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }
}
