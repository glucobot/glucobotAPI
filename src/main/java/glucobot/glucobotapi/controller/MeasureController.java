package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.Category;
import glucobot.glucobotapi.data.model.Measure;
import glucobot.glucobotapi.data.model.MeasureCollection;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.dto.AddMeasuresDto;
import glucobot.glucobotapi.dto.MeasureCollectionDto;
import glucobot.glucobotapi.service.MeasureService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("measures")
public class MeasureController {

    private final MeasureService measureService;
    private final ModelMapper modelMapper;

    public MeasureController(MeasureService measureService, ModelMapper modelMapper) {
        this.measureService = measureService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority(" + Category.PATIENT + ")")
    public MeasureCollectionDto getMeasures(Authentication authentication,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime minTimestamp,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                    LocalDateTime maxTimestamp) {

        User user = (User) authentication.getPrincipal();

        MeasureCollection measureCollection = measureService.getMeasuresForUser(user, minTimestamp, maxTimestamp);

        return modelMapper.map(measureCollection, MeasureCollectionDto.class);

    }

    @PostMapping("")
    @PreAuthorize("hasAuthority(" + Category.PATIENT + ")")
    public void addMeasure(Authentication authentication, @RequestBody @Valid AddMeasuresDto addMeasuresDto) {
        User user = (User) authentication.getPrincipal();

        List<Measure> measures = addMeasuresDto.getMeasures().stream()
                .map(measureDto -> {
                    Measure measure = modelMapper.map(measureDto, Measure.class);
                    measure.setUserId(user.getId());
                    return measure;
                }).collect(Collectors.toList());

        measureService.addMeasures(measures);
    }

}
