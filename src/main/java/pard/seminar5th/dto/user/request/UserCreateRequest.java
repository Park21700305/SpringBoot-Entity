package pard.seminar5th.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.seminar5th.entity.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreateRequest {
    private String name;
    private Integer age;

    // builder 패턴을 사용하면, 순서에 상관없이 객체를 생성할 수 있다.
    public User toEntity() {
        return User.builder()
                .name(name)
                .age(age)
                .build();
    }

//    public User toEntity() {
//        return new User(name, age);
//    }

}
