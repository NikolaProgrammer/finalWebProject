package by.gsu.epamlab.helpers;

import java.sql.Date;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.model.enums.Section;

public class Validator {

	public static void checkInputResult(String login, String password) throws ValidationException {
		
		if (login == null || password == null) {
			throw new ValidationException(Constants.ERROR_USER_OR_PASSWORD_ABSENT);
		}
		
		login = login.trim();
		if (login.equals(Constants.EMPTY_LINE)) {
			throw new ValidationException(String.format(Constants.ERROR_EMPTY_PARAMETERS, Constants.LOGIN));
		}
		
		password = password.trim();
		if (password.equals(Constants.EMPTY_LINE)) {
			throw new ValidationException(String.format(Constants.ERROR_EMPTY_PARAMETERS, Constants.PASSWORD));
		}

	}

	public static void checkTaskContent(String content) throws ValidationException {
		if (content == null || content.equals(Constants.EMPTY_LINE)) {
			throw new ValidationException(Constants.ERROR_EMPTY_TASK_CONTENT);
		}
	}

	public static Date checkTaskDate(String dateStr, Section section) throws ValidationException {

		if (section == Section.TODAY || section == Section.TOMORROW) {
			return section.getDate();
		}

		if (dateStr == null || dateStr.equals(Constants.EMPTY_LINE)) {
			throw new ValidationException(Constants.ERROR_EMPTY_TASK_DATE);
		}

		try {
			return Date.valueOf(dateStr);
		} catch (IllegalArgumentException e) {
			throw new ValidationException(Constants.ERROR_INCORRECT_TASK_DATE);
		}

	}

	public static Section checkSection(Section section) {

		if (section == null) {
			return Section.TODAY;
		}

		return section;
	}

	public static int[] checkTaskIds(String[] taskIdsStr) {

		if (taskIdsStr == null) {
			return new int[0];
		}

		int[] taskIds = new int[taskIdsStr.length];
		for (int i = 0; i < taskIds.length; i++) {
			taskIds[i] = Integer.parseInt(taskIdsStr[i]);
		}

		return taskIds;
	}

	public static int checkTaskId(String taskId) throws ValidationException {
		if (taskId == null || taskId.equals(Constants.EMPTY_LINE)) {
			throw new ValidationException(Constants.ERROR_EMPTY_TASK_ID);
		}

		return Integer.parseInt(taskId);
	}
}
