package nykon.Domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Data
@Builder
@Getter
@Setter
public class Room {
    private  Integer id;
    private String name;
    private ComfortLevel comfortLevel;
    private BigDecimal price;
    private Integer adultCapacity;
    private Integer childCapacity;

}
