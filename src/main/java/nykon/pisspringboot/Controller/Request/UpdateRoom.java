package nykon.pisspringboot.Controller.Request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateRoom {
    private Integer idRoom;
    private BigDecimal price;
}
