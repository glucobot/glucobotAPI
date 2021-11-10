package glucobot.glucobotapi.data.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measure", schema = "public")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "insulin", nullable = false)
    private int insulin;

    @Column(name = "glycemia", nullable = false)
    private int glycemia;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Measure() {
    }

    public Measure(LocalDateTime timestamp, int insulin, int glycemia, Long userId) {
        this.timestamp = timestamp;
        this.insulin = insulin;
        this.glycemia = glycemia;
        this.userId = userId;
    }

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
