package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.ConfigurationManager;
import org.resource.MessageManager;
import org.service.MetalService;

import validate.Validate;

import static org.resource.Constant.*;

import java.io.IOException;

public class SaveMetalCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String page = null;
		MetalService metalService = new MetalService();

		String title = request.getParameter(PARAM_NAME_TITLE);
		String quantity = request.getParameter(PARAM_NAME_QUANTITY);
		// int price = Integer.parseInt(request.getParameter(PARAM_NAME_PRICE));

		if (Validate.checkNameFiled(title) && Validate.checkNameFiled(quantity)
				&& Validate.checkNumber(request.getParameter(PARAM_NAME_PRICE))) {
			
			int price = Integer.parseInt(request.getParameter(PARAM_NAME_PRICE));
			if (metalService.addMetal(title, quantity, price)) {

				try {
					response.sendRedirect("controller?command=view_metals");
				} catch (IOException e) {
					e.printStackTrace();
				}
				page = ConfigurationManager.getProperty("path.page.viewMetal");
			} else {
				request.setAttribute("errorUseMetal", MessageManager.getProperty("message.metalUseError"));
				page = ConfigurationManager.getProperty("path.page.addMetal");
			}
		} else {
			request.setAttribute("errorAddMetal", MessageManager.getProperty("message.inputMetalError"));
			page = ConfigurationManager.getProperty("path.page.addMetal");
		}
		return page;
	}
}