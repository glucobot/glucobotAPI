package glucobot.glucobotapi.data.model;

import java.time.LocalDateTime;
import java.util.List;

public class MeasureCollection {

    private int count;
    private LocalDateTime firstTimestamp;
    private LocalDateTime lastTimestamp;
    private List<Measure> measures;

    public MeasureCollection(LocalDateTime firstTimestamp, LocalDateTime lastTimestamp, List<Measure> measures) {
        this.firstTimestamp = firstTimestamp;
        this.lastTimestamp = lastTimestamp;
        this.measures = measures;
        this.count = measures.size();
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

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
