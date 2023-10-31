package pard.seminar5th.repository.user;

import pard.seminar5th.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//  Optional<User> findByName(String name);

}
