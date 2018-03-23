package by.gsu.epamlab.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.NoSuchUserException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.UserDAOFactory;


public class LoginController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
		String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
		
		try {
			Validator.checkInputResult(login, password);
			IUserDAO userDAO = UserDAOFactory.getTypeDAO();
			HttpSession session = request.getSession();
			User user = userDAO.getUser(login, password);
			session.setAttribute(ConstantsJSP.KEY_USER, user);
			forward(Constants.TASKS_CONTROLLER, request, response);
		} catch (ValidationException | NoSuchUserException e) {
			forwardWarning(Constants.LOGIN_PAGE, e.getMessage(), request, response);
		} catch (DAOException e) {
			forwardError(e.getMessage(), request, response);
		} 
	}

}
