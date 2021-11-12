package glucobot.glucobotapi.service;

import glucobot.glucobotapi.data.model.Measure;
import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.data.repository.MeasureRepository;
import glucobot.glucobotapi.dto.AddMeasureDto;
import glucobot.glucobotapi.dto.MeasureDto;
import glucobot.glucobotapi.dto.MeasuresDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasureService {

    private static final int MAX_DAYS = 3;
    private static final int MAX_RESULTS = MAX_DAYS * 24 * 60;

    private static final LocalDateTime MIN_TIMESTAMP = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    private static final LocalDateTime MAX_TIMESTAMP = LocalDateTime.of(2500, 1, 1, 0, 0, 0);

    private final MeasureRepository measureRepository;
    private final ModelMapper modelMapper;


    public MeasureService(MeasureRepository measureRepository, ModelMapper modelMapper) {
        this.measureRepository = measureRepository;
        this.modelMapper = modelMapper;
    }

    public MeasuresDto getMeasuresForUser(User user, LocalDateTime minTimestamp, LocalDateTime maxTimestamp) {
        if (minTimestamp == null) {
            minTimestamp = MIN_TIMESTAMP;
        }

        if (maxTimestamp == null) {
            maxTimestamp = MAX_TIMESTAMP;
        }

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

    public void addMeasure(User user, AddMeasureDto addMeasureDto) {
        Measure measure = modelMapper.map(addMeasureDto, Measure.class);
        measure.setUserId(user.getId());
        measureRepository.save(measure);
    }

}
