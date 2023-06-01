package nykon.pisspringboot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
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
