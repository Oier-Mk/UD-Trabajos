package appointments;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

public class Day implements Comparable<Day> , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TreeSet<Appointment> AppointmentsPerDay = new TreeSet<>(); 
	public Date day;
	
	public Day(Date date) {
		
		day = date;
		
	}


	
	/**
	 * @return RETURNS THE DAY
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param ASSIGNS THE DAY
	 */
	public void setDay(Date day) {
		this.day = day;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (AppointmentsPerDay == null) {
			if (other.AppointmentsPerDay != null)
				return false;
		} else if (!AppointmentsPerDay.equals(other.AppointmentsPerDay))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		return true;
	}


	@Override
	public int compareTo(Day o) {
		return (int) ((day.getTime() - o.day.getTime())/1000); 
	}



	/**
	 * @return RETURNS THE APPOINTMENTS OF A DAY
	 */
	public TreeSet<Appointment> getAppointmentsPerDay() {
		return AppointmentsPerDay;
	}



	/**
	 * @param appointmentsPerDay ASSIGNS THE APPOINTMENTS OF A DAY
	 */
	public void setAppointmentsPerDay(TreeSet<Appointment> appointmentsPerDay) {
		AppointmentsPerDay = appointmentsPerDay;
	}



	/**
	 *	SHOWS THE DAY AND THE APPOINTMENTS OF THAT DAY
	 */
	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		return dt.format(day) + ": Appointment:" + AppointmentsPerDay;
	}


}
