package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.Category;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.dto.AddMeasureDto;
import glucobot.glucobotapi.dto.MeasuresDto;
import glucobot.glucobotapi.service.MeasureService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("measures")
public class MeasureController {

    private MeasureService measureService;

    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority(" + Category.PATIENT + ")")
    public MeasuresDto getMeasures(Authentication authentication,
                                   @RequestParam(required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime maxTimestamp,
                                   @RequestParam(required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime minTimestamp) {

        User user = (User) authentication.getPrincipal();
        return measureService.getMeasuresForUser(user, minTimestamp, maxTimestamp);

    }

    @PostMapping("")
    @PreAuthorize("hasAuthority(" + Category.PATIENT + ")")
    public void addMeasure(Authentication authentication, @RequestBody @Valid AddMeasureDto addMeasureDto) {
        User user = (User) authentication.getPrincipal();
        measureService.addMeasure(user, addMeasureDto);
    }

}
