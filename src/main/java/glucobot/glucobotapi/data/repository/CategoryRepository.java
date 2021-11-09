package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
