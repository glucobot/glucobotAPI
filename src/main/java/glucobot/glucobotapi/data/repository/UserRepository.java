package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
