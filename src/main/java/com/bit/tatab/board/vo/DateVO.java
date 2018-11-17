package com.bit.tatab.board.vo;

import java.util.Calendar;

public class DateVO {
	

	private Calendar cal = Calendar.getInstance();
	private int year;
	private int month;
	private int date;
	private int hour;
	private int min;
	private int sec;
	private int dday;
	
	public DateVO() {
		super();
	}
	
	public String nowDate() {

		this.year = cal.get(cal.YEAR);
		this.month = cal.get(cal.MONTH) + 1;
		this.date = cal.get(cal.DATE);
		
		
		String day = String.valueOf(getYear() + "." + getMonth() + "." + getDate());
		
		return day;
	}
	
	public String nowTime() {

		this.hour = cal.get(cal.HOUR_OF_DAY);
		this.min = cal.get(cal.MINUTE);
		this.sec = cal.get(cal.SECOND);
		
		String time = String.valueOf(getHour() + ":" + getMin());// + ":" + getSec());
		
		return time;	
		
	}
	
	public int calDDay(String day) {
		String[] yearMonthDate = day.split(".");
		
		System.out.println("year : " + yearMonthDate[0]);
		System.out.println("month : " + yearMonthDate[1]);
		System.out.println("date : " + yearMonthDate[2]);
		
		int year = Integer.parseInt(yearMonthDate[0]);
		int month = Integer.parseInt(yearMonthDate[1]);
		int date = Integer.parseInt(yearMonthDate[2]);
		
		Calendar dday = Calendar.getInstance();
		dday.set(year, month - 1, date);
		
		long l_dday = dday.getTimeInMillis()/(24*60*60*1000);
		long l_today = cal.getTimeInMillis()/(24*60*60*1000);
		
		long caldday = l_today - l_dday;
		
		System.out.println("d-day : " + String.valueOf(caldday));
		
		int d = (int)caldday;
		setDday(d);
		
		return d;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public int getDday() {
		return dday;
	}

	public void setDday(int dday) {
		this.dday = dday;
	}
	
	
	

	
}
