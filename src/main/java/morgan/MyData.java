package morgan;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    private String modifier;

    @Basic(optional = false)
    @Column(name = "timestamp", updatable = false)
    private Long timestamp;

}
