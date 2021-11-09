package glucobot.glucobotapi.data.model;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
