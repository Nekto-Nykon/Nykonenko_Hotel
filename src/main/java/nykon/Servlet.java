package nykon;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import nykon.Controller.Commands.*;
import nykon.Controller.Commands.GET.LoginUsrGet;
import nykon.Controller.Commands.GET.LogoutUsrGet;
import nykon.Controller.Commands.GET.RegisterUsrGet;
import nykon.Controller.Commands.GET.UsrInfoGet;
import nykon.Controller.Commands.POST.LoginUsrPost;
import nykon.Controller.Commands.POST.RegisterUsrPost;
import nykon.Controller.Webpage;
import nykon.Service.OrderService;
import nykon.Service.RoleService;
import nykon.Service.RoomService;
import nykon.Service.UsrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Servlet extends HttpServlet {
    private final  UsrService usrService = new UsrService();
    private final  RoleService roleService = new RoleService();
    private final RoomService roomService = new RoomService();
    private final OrderService orderService = new OrderService();
    private Map<String, Command> commandGetMap = new HashMap<>();
    private Map<String, Command> commandPostMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Servlet.class);


    public void init()  {
        commandGetMap.put(Webpage.LOGIN_PAGE, new LoginUsrGet());
        commandGetMap.put(Webpage.REGISTER_PAGE, new RegisterUsrGet());
        commandGetMap.put(Webpage.LOGOUT_PAGE, new LogoutUsrGet());
        commandGetMap.put(Webpage.USER_INFO_PAGE, new UsrInfoGet());

        commandPostMap.put(Webpage.LOGIN_PAGE, new LoginUsrPost(usrService, roleService));
        commandPostMap.put(Webpage.REGISTER_PAGE, new RegisterUsrPost(usrService,roleService));

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        logger.info(String.format("requested page -> %s", path));

        if (path.contains("redirect:")) {
            response.sendRedirect(path.replaceAll("/redirect:", ""));
            return;
        }
        Command command;
        if(request.getMethod().equals("POST")){
            command = commandPostMap.getOrDefault(path , (r)->Webpage.INDEX_PAGE);
        }else {
            command = commandGetMap.getOrDefault(path , (r)->Webpage.INDEX_PAGE);
        }
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replaceAll("/redirect:", ""));
            return;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}