package pard.seminar5th.entity.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pard.seminar5th.entity.user.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    // 이 책을 빌린 User의 정보를 담는 필드
    /*
    @ManyToOne(fetch = FetchType.LAZY)에서 fetch = FetchType.LAZY는 지연 로딩(Lazy Loading)을 의미.
    지연 로딩은 실제로 데이터가 필요할 때까지 데이터베이스에서 데이터를 가져오지 않는 기능입니다.
    예를 들어, 우리가 Book 엔티티를 데이터베이스에서 조회할 때, 지연 로딩이 설정된 User 필드(빌린 사용자)는 처음에는 로드되지 않음!
    그.런.데 나중에 프로그램에서 book.getUser()와 같은 방식으로 사용자 정보에 접근하려고 할 때,
    딱 그때 데이터베이스에서 해당 사용자 정보를 가져옴 ㅇㅋ?
    이렇게 지연 로딩을 사용하면 불필요한 데이터베이스 접근을 줄일 수 있어 성능 최적화에 도움이 됨 약간 db최적화 좀 치는척할때 좋음.
    */
    //    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Builder
    public Book(String title) {
        this.title = title;
    }

    // 책이 이미 빌려진 상태인지 확인
    public boolean isBorrowed() {
        return user != null;
    }

}
