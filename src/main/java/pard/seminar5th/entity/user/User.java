package pard.seminar5th.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.seminar5th.entity.book.Book;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    private Integer age;

    /*
    @OneToMany: User 엔터티 한 명이 여러 개의 Book 엔터티를 가질 수 있다는 것을 나타냄
                즉, 한 사용자가 여러 권의 책을 빌릴 수 있다는 관계를 표현
    mappedBy = "user": Book 엔터티에 있는 user 필드를 통해 이 관계가 매핑되었다는 것
                즉, Book 엔터티의 user 필드가 이 관계의 주인이라는 의미.
                mappedBy가 있는 엔티티는 "아 지금 이 엔티티와 연결된 엔티티가 주인이구나" 라고 생각하면 됨.
    cascade = CascadeType.ALL: User 엔터티에 변경이 발생했을 때,
              해당 사용자가 빌린 모든 책(borrowedBooks 목록에 있는 책들)에도 그 변경이 적용되도록 함
              예를 들어, 사용자를 데이터베이스에서 삭제하면, 그 사용자가 빌린 모든 책도 함께 삭제됨
              cascade(폭포처럼 흐르는 느낌을 연상). 여러 옵션이 있으니 참고
    orphanRemoval = true: User 엔터티에서 borrowedBooks 목록에서 특정 책을 제거하면,
                    그 Book 엔터티도 데이터베이스에서 삭제됨
                    즉, 더 이상 부모(여기서는 User)와 연관되지 않는 "고아"가 되는 Book객체를 자동으로 삭제하도록 설정
간단하게 비유하면, 이 코드는 한 사람(User)이 여러 권의 책(Book)을 빌릴 수 있고,
그 사람이 빌린 책의 목록을 관리하는 책가방(borrowedBooks) 같은 것 그리고 책을 가방에서 빼면, 그 책은 사라짐.
    */
    /*
    저는 이래도 이해가 안됐음.
    cascade = CascadeType.ALL: 이 옵션은 User 엔터티의 변경을 Book 엔터티에도 반영하겠다는 의미
       예시:
        a. User 엔터티를 데이터베이스에서 삭제하면, 해당 User가 빌린 모든 Book 엔터티도 함께 삭제됩니다.
        b. 만약 User 객체에 새로운 Book 객체를 추가하고, 이 User 객체를 저장하면, 이 새로운 Book 객체도 자동으로 데이터베이스에 저장됩니다.

    orphanRemoval = true: 이 옵션은 User의 borrowedBooks 목록에서 Book 객체를 제거할 때, 해당 Book 객체를 데이터베이스에서도 삭제하겠다는 의미입니다.
      예시:
        a. User 객체의 borrowedBooks 리스트에서 특정 Book 객체를 제거하고 저장하면, 그 Book 객체는 데이터베이스에서도 삭제됩니다.

    cascade의 동작 예시: 수진이라는 User가 있고, 그녀가 "Harry Potter"와 "Lord of the Rings" 두 권의 책을 빌렸다고 가정합니다.
                      만약 수진(User 엔터티)를 데이터베이스에서 삭제하면, "Harry Potter"와 "Lord of the Rings" 두 권의 Book 엔터티도 함께 삭제됩니다.
    orphanRemoval의 동작 예시: 위와 같이 수진이 "Harry Potter"와 "Lord of the Rings" 두 권의 책을 빌렸다고 가정합니다.
                            수진이 "Harry Potter" 책을 반납했다고 해서, 프로그램 상에서 borrowedBooks 리스트에서 이 책을 제거합니다.
                            이 변경을 저장하면, "Harry Potter"라는 Book 엔터티는 데이터베이스에서도 삭제됩니다.
        */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    private List<Book> borrowedBooks = new ArrayList<>();

    @Builder
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void update(String name, Integer age) {
        if (name != null || !name.isEmpty()) {
            this.name = name;
        }

        if (age != null || !age.equals(0)) {
            this.age = age;
        }
    }

}
