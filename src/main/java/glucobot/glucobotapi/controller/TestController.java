package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.repository.CategoryRepository;
import glucobot.glucobotapi.data.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TestController(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        var categories = categoryRepository.findAll();

        categories.forEach(cat -> System.out.println(cat.toString()));

        var users = userRepository.findAll();

        users.forEach(user -> System.out.println(user.toString()));

        return String.format("Hello %s!", name);
    }
}
