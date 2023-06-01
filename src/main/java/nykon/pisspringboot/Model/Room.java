package nykon.pisspringboot.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ComfortLevel comfortLevel;
    private BigDecimal price;
    private Integer adultCapacity;
    private Integer childCapacity;
}
