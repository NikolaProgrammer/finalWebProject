package by.gsu.epamlab.controllers.tasks;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.CannotDeleteFileException;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.SkipOperationExeption;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OperationController extends AbstractController {

	private static final long serialVersionUID = 7675757973724849179L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] tasksIdsStr = request.getParameterValues(ConstantsJSP.KEY_TASK_ID);
		String operationStr = request.getParameter(ConstantsJSP.KEY_OPERATION);
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(ConstantsJSP.KEY_USER);
		int[] taskIds = Validator.checkTaskIds(tasksIdsStr);
		Operation operation = Operation.convertFromString(operationStr);
		ITaskDAO dao = TaskDAOFactory.getTaskDAO();

		try {
			operation.execute(taskIds, user, dao);
			response.sendRedirect(Constants.PREFIX + Constants.TASKS_CONTROLLER);
		} catch (DAOException e) {
			forwardError(e.getMessage(), request, response);
		} catch (SkipOperationExeption e) {
			forward(Constants.ADD_TASKS_PAGE, request, response);
		} catch (CannotDeleteFileException e) {
			forwardWarning(Constants.TASKS_CONTROLLER, e.getMessage(), request, response);
		}

	}

}
