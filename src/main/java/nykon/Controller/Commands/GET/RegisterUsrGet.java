package nykon.Controller.Commands.GET;

import jakarta.servlet.http.HttpServletRequest;
import nykon.Controller.Commands.Command;

public class RegisterUsrGet implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "register.jsp";
    }
}
