package model;

import java.util.Calendar;

public class CalendarBean {
	
	
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	private static int month = Calendar.getInstance().get(Calendar.MONTH)+1;
	private static int day = Calendar.getInstance().get(Calendar.DATE);
	private static int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	static int clickedYear,clickedMonth;
	static String clickedDay;
	public static int getClickedYear() {
		return clickedYear;
	}

	public static void setClickedYear(int clickedYear) {
		CalendarBean.clickedYear = clickedYear;
	}

	public static int getClickedMonth() {
		return clickedMonth;
	}

	public static void setClickedMonth(int clickedMonth) {
		CalendarBean.clickedMonth = clickedMonth;
	}

	public static String getClickedDay() {
		return clickedDay;
	}

	public static void setClickedDay(String clickedDay) {
		CalendarBean.clickedDay = clickedDay;
	}

	private String days[] = new String[42]; // max size of a month is 6 lines multi 7 days
	
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth () {
		return month;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public static int getDayOfWeek() {
		return dayOfWeek;
	}

	public static void setDayOfWeek(int dayOfWeek) {
		CalendarBean.dayOfWeek = dayOfWeek;
	}

	public String[] getDays() {
		
		Calendar calendar = Calendar.getInstance();
		days = new String[42];
		calendar.set(year, month-1, 1); // set the calendar with first day of the month
		int firstDayInWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; // get the day of the week of first day
		int totalDay = calendar.getActualMaximum(Calendar.DATE); // get the max day of current month
		
		for (int i = firstDayInWeek, n=1; i< firstDayInWeek + totalDay; i++) { // initial the day array in the month
			days[i] = String.valueOf(n);
			n++;
		}
//		for(String day: days)
//			System.out.println(day);
		return days;
	}
}
