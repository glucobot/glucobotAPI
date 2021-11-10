package glucobot.glucobotapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

@RestController("measures")
public class MeasureController {

    private final ModelMapper modelMapper;

    public MeasureController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
