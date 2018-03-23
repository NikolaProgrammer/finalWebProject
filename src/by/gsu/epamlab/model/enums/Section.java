package by.gsu.epamlab.model.enums;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import by.gsu.epamlab.model.impl.database.ConstantsSQL;

public enum Section {
	TODAY("Today", ConstantsSQL.SELECT_TASKS_TODAY) {
	
		@Override
		public Date getDate() {
			Calendar calendar = Calendar.getInstance();
			return new Date(calendar.getTimeInMillis());
		}
		
	},
	TOMORROW("Tomorrow", ConstantsSQL.SELECT_TASKS_TOMORROW) {
	
		@Override
		public Date getDate() {
			final int ADDED_DAYS = 1;
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, ADDED_DAYS);
			return new Date(calendar.getTimeInMillis());
		}
		
	},
	SOMEDAY("Someday", ConstantsSQL.SELECT_TASKS_SOMEDAY) {
		
		@Override
		public Date getDate() {
			return  Section.TOMORROW.getDate();
		}
		
	},
	FIXED("Fixed", ConstantsSQL.SELECT_TASKS_FIXED){

		@Override
		public void setQuery(PreparedStatement prStatement) throws SQLException {}
		
	},
	
	RECYCLE_BIN("Recycle bin", ConstantsSQL.SELECT_TASKS_RECYCLE_BIN){

		@Override
		public void setQuery(PreparedStatement prStatement) throws SQLException {}
		
	};
	
	private String name;
	private String query;
	
	private Section(String name, String query) {
		this.name = name;
		this.query = query;
	}
	
	public String getName() {
		return name;
	}
	
	public String getQuery() {
		return query;
	}
	
	public  void setQuery(PreparedStatement prStatement) throws SQLException {
		final int DATE_INDEX = 2;
		prStatement.setDate(DATE_INDEX, getDate());
	}
	
	public Date getDate() {
		return null;
	}
	
	public static Section convertFromString(String sectionName) {
		Section section = Section.TODAY;
		
		for (Section item : values()) {
			if (item.name.equals(sectionName)) {
				section = item;
			}
		}
		
		return section;
	}

}
