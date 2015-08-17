package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;
import org.service.MetalService;

public class ViewMetalCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.viewMetal");
        MetalService metalService = new MetalService();
        request.setAttribute("metal", metalService.readSubstances());
        return page;
    }
}