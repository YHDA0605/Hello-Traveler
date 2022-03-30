package com.teamHT.helloTraveler.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatingVO {
	
	private Date startDate;
	private Date endDate;
	private int mon = 0;
	private int tue = 0;
	private int wed = 0;
	private int thu = 0;
	private int fri = 0;
	private int sat = 0;
	private int sun = 0;
	private List<Date> dateList;
	
	public DatingVO() {	}
	
	public DatingVO(Date startDate, Date endDate, List<String> day) {
		setStartDate(startDate);
		setEndDate(endDate);
		
		for(int i = 0; i < day.size(); i++) {
			if(day.get(i).equals("mon")) {
				setMon(1);
			}
			if(day.get(i).equals("tue")) {
				setTue(1);
			}
			if(day.get(i).equals("wed")) {
				setWed(1);
			}
			if(day.get(i).equals("thu")) {
				setThu(1);
			}
			if(day.get(i).equals("fri")) {
				setFri(1);
			}
			if(day.get(i).equals("sat")) {
				setSat(1);
			}
			if(day.get(i).equals("sun")) {
				setSun(1);
			}
		}
		
		setDateList(dateListing());
		
	}
	
	public List<Date> dateListing() {
		List<Date> dateListing = new ArrayList<Date>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());
		
		while (getStartDate().before(getEndDate())) { // 다르다면 실행, 동일 하다면 빠져나감

			if (getSun() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getMon() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getTue() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getWed() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getThu() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getFri() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			if (getSat() == 1) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				if (cal.getTime().after(startDate) && cal.getTime().before(endDate))
					dateListing.add(cal.getTime());
			}

			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			cal.add(Calendar.DATE, 1); // 1일 더해줌
			startDate = cal.getTime(); // 비교를 위한 값 셋팅
		}
		
		return dateListing;
	}
	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMon() {
		return mon;
	}

	public void setMon(int mon) {
		this.mon = mon;
	}

	public int getTue() {
		return tue;
	}

	public void setTue(int tue) {
		this.tue = tue;
	}

	public int getWed() {
		return wed;
	}

	public void setWed(int wed) {
		this.wed = wed;
	}

	public int getThu() {
		return thu;
	}

	public void setThu(int thu) {
		this.thu = thu;
	}

	public int getFri() {
		return fri;
	}

	public void setFri(int fri) {
		this.fri = fri;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public int getSun() {
		return sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
	}

	public List<Date> getDateList() {
		return dateList;
	}

	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}
}
