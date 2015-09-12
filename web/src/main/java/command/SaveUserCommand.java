package command;

import static org.resource.Constant.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;
import org.resource.MessageManager;
import org.service.UserService;

import validate.Validate;

public class SaveUserCommand implements ActionCommand {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        String page = null;
       
        UserService userService = new UserService();
        
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
      
       
        if (Validate.checkNameFiled(firstName) && Validate.checkNameFiled(lastName)&& 
        		Validate.checkLogin(login) && Validate.checkPassword(password)) {
        	//checkForm = true;
        	if (userService.addUser(firstName, lastName, login, password)) {
            	
            	            	
               	try {
                        response.sendRedirect("controller?command=login");
                    } catch (IOException e) {
                       e.printStackTrace();
                   }
                    page = ConfigurationManager.getProperty("path.page.login");
                	
                	} else {
                		request.setAttribute("errorRegistration",
                                MessageManager.getProperty("message.loginUseError"));
                        page = ConfigurationManager.getProperty("path.page.registration");
                	} 
                }else {
                	request.setAttribute("errorInputDataMessage",
                            MessageManager.getProperty("message.inputError"));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
                
        	
        
               
        return page;
    }
}