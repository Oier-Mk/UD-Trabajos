package appointments;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Calendar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TreeMap<Integer,Day> calendar;

	public Calendar() {		
		calendar = new TreeMap<>(); 	
	}



	/**
	 * @return	RETURNS THE CALENDAR OF THE WORKER
	 */
	public TreeMap<Integer,Day> getCalendar() {
		return calendar;
	}




	/**
	 * @param ASSIGNS THE CALENDAR OF THE WORKER
	 */
	public void setCalendar(TreeMap<Integer,Day> calendarP) {
		calendar = calendarP;
	}

	/**
	 *	DISPLAYS THE CALENDAR OF THE WORKER
	 */
	public String toString() {
		String str = "";
		
		for(Entry<Integer, Day> entry : calendar.entrySet()) {
			  Day value = entry.getValue();
			  str += value.toString();
			}
		return  str;
	}






}
