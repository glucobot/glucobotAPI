package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.Category;
import glucobot.glucobotapi.data.model.Measure;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.data.repository.MeasureRepository;
import glucobot.glucobotapi.dto.AddMeasureDto;
import glucobot.glucobotapi.dto.MeasureDto;
import glucobot.glucobotapi.dto.MeasuresDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
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

    private static final int MAX_DAYS = 1;
    private static final int MAX_RESULTS = MAX_DAYS * 24 * 60;

    private static final LocalDateTime MIN_TIMESTAMP = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    private static final LocalDateTime MAX_TIMESTAMP = LocalDateTime.of(2500, 1, 1, 0, 0, 0);


    private final ModelMapper modelMapper;
    private final MeasureRepository measureRepository;

    public MeasureController(ModelMapper modelMapper, MeasureRepository measureRepository) {
        this.modelMapper = modelMapper;
        this.measureRepository = measureRepository;
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

        if (minTimestamp == null) {
            minTimestamp = MIN_TIMESTAMP;
        }

        if (maxTimestamp == null) {
            maxTimestamp = MAX_TIMESTAMP;
        }

        User user = (User) authentication.getPrincipal();

        List<Measure> measures = measureRepository.getMeasuresForUser(
                user.getId(),
                minTimestamp,
                maxTimestamp,
                PageRequest.of(0, MAX_RESULTS)
        );

        List<MeasureDto> measureDtoList = measures.stream()
                .map(measure -> modelMapper.map(measure, MeasureDto.class))
                .collect(Collectors.toList());

        LocalDateTime firstTimestamp, lastTimestamp;
        if (measures.size() == 0) {
            firstTimestamp = minTimestamp;
            lastTimestamp = maxTimestamp;
        } else {
            firstTimestamp = measures.get(measures.size() - 1).getTimestamp();
            lastTimestamp = measures.get(0).getTimestamp();
        }

        return new MeasuresDto(measureDtoList.size(), firstTimestamp, lastTimestamp, measureDtoList);

    }

    @PostMapping("")
    @PreAuthorize("hasAuthority(" + Category.PATIENT + ")")
    public void addMeasure(Authentication authentication, @RequestBody @Valid AddMeasureDto addMeasureDto) {
        User user = (User) authentication.getPrincipal();

        Measure measure = modelMapper.map(addMeasureDto, Measure.class);
        measure.setUserId(user.getId());
        measureRepository.save(measure);
    }

}
