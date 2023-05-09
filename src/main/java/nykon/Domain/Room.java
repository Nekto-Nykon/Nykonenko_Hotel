package nykon.Domain;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ComfortLevel comfortLevel;
    private BigDecimal price;
    private Integer adultCapacity;
    private Integer childCapacity;

}
