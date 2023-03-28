package nykon.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Builder
@Getter
@Setter
public class Order {
    private Integer id;
    private Integer idAccount;
    private Integer idRoom;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Status status;
    private Integer adultCapacityRequirement;
    private Integer childCapacityRequirement;
    private ComfortLevel comfortLevelRequirement;


}
