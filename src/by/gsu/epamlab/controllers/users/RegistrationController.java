package by.gsu.epamlab.controllers.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.UserAlreadyExistException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.factories.UserDAOFactory;

public class RegistrationController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
		String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
		
		try {
			Validator.checkInputResult(login, password);
			IUserDAO userDAO = UserDAOFactory.getTypeDAO();
			userDAO.addUser(login, password);
			response.sendRedirect(Constants.PREFIX + Constants.MAIN_PAGE);
		} catch (ValidationException | UserAlreadyExistException e) {
			forwardWarning(Constants.REGISTRATION_PAGE, e.getMessage(), request, response);
		} catch (DAOException e) {
			forwardError(e.getMessage(), request, response);
		} 
		
	}

}
