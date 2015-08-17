package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.resource.Constant;

import command.ActionCommand;
import command.factory.CommandFactory;

import javax.servlet.RequestDispatcher;

import javax.servlet.annotation.WebServlet;

import static org.resource.Constant.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		String paramCommand = request.getParameter(COMMAND);
		if (paramCommand == null) {
			throw new IllegalArgumentException(PARAM_COMMAND_IS_NULL);
		}
		ActionCommand command = CommandFactory.getCommand(paramCommand);

		String nextPage = command.execute(request, response);
		if (!response.isCommitted()) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
		}
	}
}