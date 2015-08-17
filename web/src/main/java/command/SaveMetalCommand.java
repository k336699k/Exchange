package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;
import org.service.MetalService;

import static org.resource.Constant.*;

import java.io.IOException;

public class SaveMetalCommand implements ActionCommand {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        String page = null;
        String title = request.getParameter(PARAM_NAME_TITLE);
        String quantity = request.getParameter(PARAM_NAME_QUANTITY);
        int price = Integer.parseInt(request.getParameter(PARAM_NAME_PRICE));
       
        MetalService metalService = new MetalService();
        metalService.addMetal(title, quantity, price);
        
        try {
            response.sendRedirect("controller?command=view_metals");
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = ConfigurationManager.getProperty("path.page.viewMetal");
        
        return page;
    }
}