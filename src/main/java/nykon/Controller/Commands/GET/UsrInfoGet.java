package nykon.Controller.Commands.GET;

import jakarta.servlet.http.HttpServletRequest;
import nykon.Controller.Commands.Command;

public class UsrInfoGet implements Command {
    public static final String USER_INFO = "user-info.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return USER_INFO;
    }
}
