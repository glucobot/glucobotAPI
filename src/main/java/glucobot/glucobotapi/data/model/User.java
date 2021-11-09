package glucobot.glucobotapi.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "user")
    private List<Measure> measures;

}
