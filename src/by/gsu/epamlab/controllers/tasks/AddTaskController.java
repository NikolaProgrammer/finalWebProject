package by.gsu.epamlab.controllers.tasks;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Section;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

public class AddTaskController extends AbstractController {

	private static final long serialVersionUID = -6372082423658122491L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String taskContent = request.getParameter(ConstantsJSP.KEY_TASK_CONTENT);
		String taskDateStr = request.getParameter(ConstantsJSP.KEY_TASK_DATE);
		HttpSession session = request.getSession(false);
		Section section = (Section) session.getAttribute(ConstantsJSP.KEY_SECTION);
		User user = (User) session.getAttribute(ConstantsJSP.KEY_USER);
		
		try {
			Validator.checkTaskContent(taskContent);
			Date taskDate = Validator.checkTaskDate(taskDateStr, section);
			Task task = new Task(taskContent, taskDate);
			ITaskDAO taskDAO = TaskDAOFactory.getTaskDAO();
			taskDAO.addTask(user, task);
			response.sendRedirect(Constants.PREFIX + Constants.TASKS_CONTROLLER);
		} catch (ValidationException e) {
			forwardWarning(Constants.ADD_TASKS_PAGE, e.getMessage(), request, response);
		} catch (DAOException e) {
			forwardError(e.getMessage(), request, response);
		}

	}

}
