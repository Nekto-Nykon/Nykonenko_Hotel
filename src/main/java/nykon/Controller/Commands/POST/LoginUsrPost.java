package nykon.Controller.Commands.POST;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.Controller.Commands.Command;
import nykon.Controller.Webpage;
import nykon.Domain.Usr;
import nykon.Service.RoleService;
import nykon.Service.UsrService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
@AllArgsConstructor
@Slf4j
public class LoginUsrPost implements Command {
    private final UsrService usrService;
    private final RoleService roleService;
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String USER_INFO_PAGE = "user-info.jsp";
    private static final Logger logger = LoggerFactory.getLogger(LoginUsrPost.class.getName());

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter("login_email");
        String password = request.getParameter("login_password");

        Optional<Usr> maybeUsr = usrService.findByEmail(email);
        if (maybeUsr.isEmpty()) {
            request.setAttribute("errorLoginPassMessage",
                    "Some wrong with your data");
            return LOGIN_PAGE;
        }
        Usr usr = maybeUsr.get();
        if (BCrypt.checkpw(password, usr.getPassword())) {
            request.getSession().setAttribute("user", usr);
            request.getSession().setAttribute("email", usr.getEmail());
            log.info(String.format("Login operation is OK -> %s", usr));
            return USER_INFO_PAGE;
        }
        return LOGIN_PAGE;
    }
}
