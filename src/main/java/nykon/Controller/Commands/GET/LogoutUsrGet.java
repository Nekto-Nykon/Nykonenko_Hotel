package nykon.Controller.Commands.GET;

import nykon.Controller.Commands.Command;
import nykon.Controller.Webpage;

import jakarta.servlet.http.HttpServletRequest;

public class LogoutUsrGet implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/redirect:" + Webpage.INDEX_PAGE;
    }
}
