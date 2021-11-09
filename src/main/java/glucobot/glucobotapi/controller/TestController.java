package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final CategoryRepository categoryRepository;

    public TestController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        var categories = categoryRepository.findAll();

        categories.forEach(cat -> System.out.println(cat.toString()));

        return String.format("Hello %s!", name);
    }
}
