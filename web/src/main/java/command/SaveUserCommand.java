package command;

import static org.resource.Constant.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;
import org.service.MetalService;
import org.service.UserService;

public class SaveUserCommand implements ActionCommand {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        String page = null;
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        
        UserService userService = new UserService();
        userService.addUser(firstName, lastName, login, password);
        
        try {
            response.sendRedirect("controller?command=login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = ConfigurationManager.getProperty("path.page.login");
        
        return page;
    }
}