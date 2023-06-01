package nykon.pisspringboot.Controller.Request;

import lombok.Data;
import nykon.pisspringboot.Model.ComfortLevel;

import java.math.BigDecimal;

@Data
public class CreateRoomData {
    private String name;
    private ComfortLevel comfortLevel;
    private BigDecimal price;
    private Integer adultCapacity;
    private Integer childCapacity;


}
