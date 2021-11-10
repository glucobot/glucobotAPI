package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(value = "UserWithCategory")
    Optional<User> findByEmail(String email);

}
