package pard.seminar5th.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.seminar5th.dto.user.request.UserCreateRequest;
import pard.seminar5th.dto.user.request.UserUpdateRequest;
import pard.seminar5th.entity.user.User;
import pard.seminar5th.repository.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // 이게 뭐라고?
public class UserService {
    private final UserRepository userRepository;

    public User save(UserCreateRequest request) {
        return userRepository.save(request.toEntity());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }

    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        user.update(request.getName(), request.getAge());

        return userRepository.save(user);
    }

    // 이 부분도 중요 casecade의 역할을 알 수 있다.
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
