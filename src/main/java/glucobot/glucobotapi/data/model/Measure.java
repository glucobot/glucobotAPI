package glucobot.glucobotapi.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "measure", schema = "public")
public class Measure {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "insulin", nullable = false)
    private int insulin;

    @Column(name = "glycemia", nullable = false)
    private int glycemia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
