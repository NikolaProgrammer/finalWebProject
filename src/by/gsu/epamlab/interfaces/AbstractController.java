package by.gsu.epamlab.interfaces;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.factories.UserDAOFactory;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String userTypeDao = getServletContext().getInitParameter(Constants.USER_DAO_TYPE);
		String taskTypeDao = getServletContext().getInitParameter(Constants.USER_DAO_TYPE);
		UserDAOFactory.initDAO(userTypeDao);
		TaskDAOFactory.initDAO(taskTypeDao);
		
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	abstract protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	protected void forward(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
		rq.forward(request, response);
	}

	protected void forwardWarning(String url, String warning, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ConstantsJSP.KEY_WARNING_MESSAGE, warning);
		RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
		rq.forward(request, response);
	}

	protected void forwardError(String error, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ConstantsJSP.KEY_ERROR_MESSAGE, error);
		RequestDispatcher rq = getServletContext().getRequestDispatcher(Constants.ERROR_PAGE);
		rq.forward(request, response);
	}
}
