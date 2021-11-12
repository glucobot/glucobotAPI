package glucobot.glucobotapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MeasuresDto {
    private int count;
    private LocalDateTime firstTimestamp;
    private LocalDateTime lastTimestamp;
    private List<MeasureDto> measures;

    public MeasuresDto(int count, LocalDateTime firstTimestamp, LocalDateTime lastTimestamp, List<MeasureDto> measures) {
        this.count = count;
        this.firstTimestamp = firstTimestamp;
        this.lastTimestamp = lastTimestamp;
        this.measures = measures;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getFirstTimestamp() {
        return firstTimestamp;
    }

    public void setFirstTimestamp(LocalDateTime firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    public LocalDateTime getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(LocalDateTime lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public List<MeasureDto> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasureDto> measures) {
        this.measures = measures;
    }
}
