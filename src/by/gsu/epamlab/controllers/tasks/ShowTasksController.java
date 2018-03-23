package by.gsu.epamlab.controllers.tasks;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Section;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

public class ShowTasksController extends AbstractController {

	private static final long serialVersionUID = 5486707661176301483L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sectionString = (String) request.getAttribute(ConstantsJSP.KEY_SECTION_ATTRIBUTE);
		Section section = Section.convertFromString(sectionString);
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(ConstantsJSP.KEY_USER);
		ITaskDAO taskDAO = TaskDAOFactory.getTaskDAO();
		
		try {
			List<Task> tasks = taskDAO.getTasks(user, section);
			session.setAttribute(ConstantsJSP.KEY_SECTION, section);
			request.setAttribute(ConstantsJSP.KEY_TASKS, tasks);
			forward(Constants.TASKS_PAGE, request, response);
		} catch (DAOException e) {
			forwardError(e.getMessage(), request, response);
		}
	}

}
