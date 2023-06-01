package nykon.pisspringboot.Controller.Request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Dates {
    private LocalDate checkIn;
    private LocalDate checkOut;

}
