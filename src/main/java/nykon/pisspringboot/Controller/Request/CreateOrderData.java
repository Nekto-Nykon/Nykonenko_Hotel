package nykon.pisspringboot.Controller.Request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateOrderData {
    private Integer idRoom;
    private String email;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
