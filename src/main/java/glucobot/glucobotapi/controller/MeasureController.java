package glucobot.glucobotapi.controller;

import glucobot.glucobotapi.data.model.Measure;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.data.repository.MeasureRepository;
import glucobot.glucobotapi.dto.MeasureDto;
import glucobot.glucobotapi.dto.MeasuresDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("measures")
public class MeasureController {

    private static final int MAX_DAYS = 1;
    private static final int MAX_RESULTS = MAX_DAYS * 24 * 60;

    private static final LocalDateTime MIN_TIMESTAMP = LocalDateTime.of(1970, 1, 1, 0, 0, 0);

    private final ModelMapper modelMapper;
    private final MeasureRepository measureRepository;

    public MeasureController(ModelMapper modelMapper, MeasureRepository measureRepository) {
        this.modelMapper = modelMapper;
        this.measureRepository = measureRepository;
    }

    @GetMapping("")
    public MeasuresDto getMeasures(Authentication authentication,
                                   @RequestParam(required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                           LocalDateTime timestamp) {

        if (timestamp == null)
            timestamp = MIN_TIMESTAMP;

        User user = (User) authentication.getPrincipal();

        List<Measure> measures = measureRepository.getMeasuresForUser(
                user.getId(),
                timestamp,
                PageRequest.of(0, MAX_RESULTS)
        );

        List<MeasureDto> measureDtoList = measures.stream()
                .map(measure -> modelMapper.map(measure, MeasureDto.class))
                .collect(Collectors.toList());

        LocalDateTime lastTimestamp;
        if (measures.size() == 0) {
            lastTimestamp = MIN_TIMESTAMP;
        } else {
            lastTimestamp = measures.get(0).getTimestamp();
        }

        return new MeasuresDto(measureDtoList.size(), lastTimestamp, measureDtoList);

    }

}
