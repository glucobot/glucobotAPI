package glucobot.glucobotapi.dto;

import java.time.LocalDateTime;

public class MeasureDto {
    private LocalDateTime timestamp;

    private int insulin;

    private int glycemia;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public int getGlycemia() {
        return glycemia;
    }

    public void setGlycemia(int glycemia) {
        this.glycemia = glycemia;
    }
}
