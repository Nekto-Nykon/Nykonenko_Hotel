package nykon.Domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Usr {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Role role;

}
