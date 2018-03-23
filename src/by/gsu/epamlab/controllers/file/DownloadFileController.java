package by.gsu.epamlab.controllers.file;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.helpers.FileHandler;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileController extends AbstractController {

	private static final long serialVersionUID = 1970910173705998543L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String taskIdStr = request.getParameter(ConstantsJSP.KEY_HIDDEN_TASK_ID);
		
		try {
			int taskId = Validator.checkTaskId(taskIdStr);
			ITaskDAO taskDAO = TaskDAOFactory.getTaskDAO();
			Task task = taskDAO.getTaskById(taskId);
			FileHandler.downloadFile(task.getTrueFileName(), task.getSystemFileName(), response);
			response.sendRedirect(Constants.PREFIX + Constants.TASKS_CONTROLLER);
		} catch (ValidationException | DAOException e) {
			forwardError(e.getMessage(), request, response);
		} 
	}
	
}
