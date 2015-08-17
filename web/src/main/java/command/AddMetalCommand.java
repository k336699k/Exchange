package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;

public class AddMetalCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        return ConfigurationManager.getProperty("path.page.addMetal");
    }
}