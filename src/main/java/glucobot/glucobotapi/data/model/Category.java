package glucobot.glucobotapi.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category", schema = "public")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<User> users;
}
