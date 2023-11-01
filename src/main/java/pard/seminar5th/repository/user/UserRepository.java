package pard.seminar5th.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pard.seminar5th.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

//  Optional<User> findByName(String name);

}
