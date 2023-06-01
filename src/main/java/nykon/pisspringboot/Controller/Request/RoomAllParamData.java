package nykon.pisspringboot.Controller.Request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import nykon.pisspringboot.Model.ComfortLevel;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class RoomAllParamData {

    private LocalDate checkin;
    private LocalDate checkOut;
    @Enumerated(value = EnumType.STRING)
    private ComfortLevel comfortLevel;
    private Integer adultCapacity;
    private Integer childCapacity;
}
