package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.dto.UserWithCategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final ModelMapper modelMapper;

    public LoginController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public UserWithCategoryDto login(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        System.out.println(user.getCategory());
        UserWithCategoryDto dto = modelMapper.map(user, UserWithCategoryDto.class);

        System.out.println(dto.getCategory());

        return dto;
    }

}
