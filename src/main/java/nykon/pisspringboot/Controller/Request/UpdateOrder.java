package nykon.pisspringboot.Controller.Request;

import lombok.Data;
import nykon.pisspringboot.Model.Status;

@Data
public class UpdateOrder {
    private Integer idOrder;
    private Status status;
}
