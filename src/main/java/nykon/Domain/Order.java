package nykon.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idAccount;
    private Integer idRoom;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer adultCapacityRequirement;
    private Integer childCapacityRequirement;
    @Enumerated(EnumType.STRING)
    private ComfortLevel comfortLevelRequirement;


}
