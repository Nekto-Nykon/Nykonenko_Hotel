package nykon.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.List;
@Data
@Builder
@Getter
@Setter
public class Role {
    private Integer id;
    private String name;
    @OneToMany
    private List<Usr> users;
}
