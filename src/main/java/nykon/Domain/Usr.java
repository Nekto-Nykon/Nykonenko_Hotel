package nykon.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "usrs")
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @ManyToOne
    private Role role ;

}
