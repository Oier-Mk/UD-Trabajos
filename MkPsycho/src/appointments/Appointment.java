package appointments;

import java.io.Serializable;
import java.util.*;

import people.Person;

/**
 * @author Oier Mentxaka
 *
 */
@SuppressWarnings("serial")
public class Appointment implements Serializable, Comparable<Appointment>{
	private int minute;
	private int hour;
	private int day;
	private int month;
	private int year;
	private Person person;
	private String name;
	private String surname;
	private Date date;
	private String Id;
	private boolean duration; 

	@SuppressWarnings("deprecation")
	public Appointment(Person person,  int year,int month, int day,int hour, int minute) {
		this.person = person;
		setName(person);
		setSurname(person);
		setMinute(minute);
		setHour(hour);
		setDay(day);
		setMonth(month);
		setYear(year);

		date = new Date(getYear(), getMonth(), getDay(), getHour(), getMinute());

		String Smonth = String.valueOf(getMonth()+1);

		Smonth = TWOdecimals(Smonth);

		String Sday = String.valueOf(getDay());

		Sday = TWOdecimals(Sday);

		String Shour = String.valueOf(getHour());

		Shour = TWOdecimals(Shour);

		String Sminute = String.valueOf(getMinute());

		Sminute = TWOdecimals(Sminute);

		Id = String.valueOf(getYear()) + Smonth + Sday + Shour + Sminute;

	}

	/**
	 * @return RETURNS THE MINUTES IN WHICH THE PERSON WILL ARRIVE
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @param minute ASSIGNS THE MINUTE IN WHICH THE PERSON WILL ARRIVE
	 */
	public void setMinute(int minute) {
		if (minute >= 0) {
			this.minute = minute;
		} else {
			this.minute = 0;
		}
	}

	/**
	 * @return RETURNS THE HOUR IN WHICH THE PERSON WILL ARRIVE
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour ASSIGNS THE HOUR IN WHICH THE PERSON WILL ARRIVE
	 */
	public void setHour(int hour) {
		if (hour >= 0) {
			this.hour = hour;
		} else {
			this.hour = 0;
		}
	}

	/**
	 * @return RETURNS THE DAY IN WHICH THE PERSON WILL ARRIVE
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day ASSIGNS THE DAY IN WHICH THE PERSON WILL ARRIVE
	 */
	public void setDay(int day) {
		if (day >= 0) {
			this.day = day;
		} else {
			this.day = 0;
		}
	}

	/**
	 * @return RETURNS THE MONTH IN WHICH THE PERSON WILL ARRIVE
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month ASSIGNS THE MONTH IN WHICH THE PERSON WILL ARRIVE
	 */
	public void setMonth(int month) {
		if (month >= 0) {
			this.month = month -1 ;
		} else {
			this.month = 0;
		}
	}

	/**
	 * @return RETURNS YEAR MONTH IN WHICH THE PERSON WILL ARRIVE
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year ASSIGNS THE YEAR IN WHICH THE PERSON WILL ARRIVE
	 */
	public void setYear(int year) {
		if (year >= 0) {
			this.year = year;
		} else {
			this.year = 0;
		}
	}

	/**
	 * @return RETURNS THE NAME OF THE PERSON WHICH WILL COME TO THE APPOINTMENT
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param person ASSIGNS THE SURNAME OF THE PERSON WHICH WILL COME TO THE
	 *               APPOINTMENT
	 */
	public void setName(Person person) {
		this.name = person.getName();
	}

	/**
	 * @return RETURNS THE SURNAME OF THE PERSON WHICH WILL COME TO THE APPOINTMENT
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param person ASSIGNS THE SURNAME OF THE PERSON WHICH WILL COME TO THE
	 *               APPOINTMENT
	 */
	public void setSurname(Person person) {
		this.surname = person.getSurname();
	}
	
	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return RETURNS THE DATE OF THE APPOINTMENT
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * ASSIGNS THE DATE OF THE APPOINTMENT
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return RETURNS THE DATE OF THE APPOINTMENT
	 */
	public String getId() {
		return Id;
	}
	/**
	 * ASSIGNS THE ID OF THE APPOINTMENT
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return RETURNS TRUE WHEN DURATION IS ONE HOUR AND A HALF, IF THE DURATION IS JUST AN HOUR, IT RETIRNS FALSE
	 */
	public boolean getDuration() {
		return duration;
	}

	public void setDuration(boolean duration) {
		this.duration = duration;
	}

	public String TWOdecimals(String string) {
		String str = "";
		if (string.length() == 1) {
			str += "0" + string;
		}
		else {
			str += string;
		}
		return str;
	}

	/**
	 * COMPARES THAT THE APPOINTMENT IS NOT REPEATED (IF THERE IS OR NOT AN
	 * APPOINTMENT AT THE SAME TIME)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "The day " + this.getDate() + " " + name + " " + surname + " will come.";
	}

	@Override
	public int compareTo(Appointment arg0) {
		return (int) (date.getTime() - arg0.date.getTime()); 
	}

}
