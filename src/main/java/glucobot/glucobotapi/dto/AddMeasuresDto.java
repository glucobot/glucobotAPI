package glucobot.glucobotapi.dto;

import java.util.ArrayList;
import java.util.List;

public class AddMeasuresDto {
    private List<MeasureDto> measures = new ArrayList<>();

    public List<MeasureDto> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasureDto> measures) {
        this.measures = measures;
    }
}
