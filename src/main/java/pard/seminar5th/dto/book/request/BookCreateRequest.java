package pard.seminar5th.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.seminar5th.entity.book.Book;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookCreateRequest {
    private String title;

    // builder 패턴을 사용하면, 순서에 상관없이 객체를 생성할 수 있다.
    public Book toEntity() {
        return Book.builder()
                .title(title)
                .build();
    }
}
