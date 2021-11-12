package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.Category;
import glucobot.glucobotapi.data.model.MeasureCollection;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.dto.MeasureCollectionDto;
import glucobot.glucobotapi.dto.UserDto;
import glucobot.glucobotapi.dto.UserWithCategoryDto;
import glucobot.glucobotapi.service.MeasureService;
import glucobot.glucobotapi.service.UserService;
import glucobot.glucobotapi.service.exception.PatientNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("patients")
public class PatientController {

    private final UserService userService;
    private final MeasureService measureService;
    private final ModelMapper modelMapper;

    public PatientController(UserService userService, MeasureService measureService, ModelMapper modelMapper) {
        this.userService = userService;
        this.measureService = measureService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority(" + Category.DOCTOR + ")")
    public List<UserDto> getPatients() {
        List<User> patients = userService.getPatients();

        return patients.stream()
                .map(patient -> modelMapper.map(patient, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority(" + Category.DOCTOR + ")")
    public UserWithCategoryDto getPatient(@PathVariable long id) throws PatientNotFoundException {
        User patient = userService.getPatientById(id);
        return modelMapper.map(patient, UserWithCategoryDto.class);
    }

    @GetMapping("{id}/measures")
    @PreAuthorize("hasAuthority(" + Category.DOCTOR + ")")
    public MeasureCollectionDto getMeasures(@PathVariable long id,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime minTimestamp,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime maxTimestamp
    ) throws PatientNotFoundException {

        User user = userService.getPatientById(id);

        MeasureCollection measureCollection = measureService.getMeasuresForUser(user, minTimestamp, maxTimestamp);

        // List<MeasureDto> measureDtoList = measures.stream()
        //        .map(measure -> modelMapper.map(measure, MeasureDto.class))
        //        .collect(Collectors.toList());

        return modelMapper.map(measureCollection, MeasureCollectionDto.class);

    }

}
