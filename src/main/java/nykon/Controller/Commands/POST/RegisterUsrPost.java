package nykon.Controller.Commands.POST;

import lombok.AllArgsConstructor;
import nykon.Controller.Commands.Command;
import nykon.Controller.Webpage;
import nykon.Domain.Usr;
import nykon.Service.RoleService;
import nykon.Service.UsrService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

@AllArgsConstructor
public class RegisterUsrPost implements Command {
    private final UsrService usrService;
    private final RoleService roleService;
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String REGISTER_PAGE = "register.jsp";
    private static final Logger logger = LoggerFactory.getLogger(RegisterUsrPost.class.getName());
    @Override
    public String execute(HttpServletRequest request) {

        String[] validationFields = {"register_name",
                "register_email",
                "register_password"};
        boolean valid = Arrays.stream(validationFields)
                .map(request::getParameter)
                .allMatch(this::validateField);
        if (!valid)
            return REGISTER_PAGE;
        Usr registerUsr = Usr.builder()
                .name(request.getParameter("register_name"))
                .email(request.getParameter("register_email"))
                .password(BCrypt.hashpw(request.getParameter("register_password"),BCrypt.gensalt()))
                .role(roleService.findById(1).get())
                .build();
        try {
            usrService.create(registerUsr);
        }
        catch (RuntimeException exception){
            logger.error(String.format("Unable to register user -> %s", exception));
        }
        return Webpage.LOGIN_PAGE;
    }
    private boolean validateField(String value) {
        if(value == null) return false;
        else if (value.equals("")) return false;
        else return true;
    }
}
