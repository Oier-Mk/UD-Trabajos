package people;

import java.util.ArrayList;

import appointments.Calendar;

@SuppressWarnings("serial")
public
class Worker extends Person {
	protected int hoursPerWeek;
	protected int officeNumber;
	protected String password;
	protected ArrayList<Person> Array;
	public Calendar calendar;

	
	public Worker(String name, String surname, int officeNumber, String email,String password, String image, String button) {
		super(name, surname, email, image, button);
		Array = new ArrayList<Person>();
		setOfficeNumber(officeNumber);
		setHoursPerWeek(hoursPerWeek);
		setPassword(password);
		calendar = new Calendar();
	}

	/**
	 * @return RETURNS THE CALENDAR OF THE WORKER
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar ASSIGNS A CALENDAR TO THE WORKER
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return RETURNS THE HOURS WORKED ON THE CORRESPONDENT WEEK
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	/**
	 * @param hoursPerWeek	ASSIGNS THE HOURS PER WEEK OF THE WORKER
	 */
	public void setHoursPerWeek(int hoursPerWeek) {
		if (hoursPerWeek >= 0) {
			this.hoursPerWeek = hoursPerWeek;
		}
		else {
			this.hoursPerWeek = errorMessageINT;
		}
			
	}
	
	/**
	 * @return	GIVES AN OFFICE NUMBER
	 */
	public int getOfficeNumber() {
		return officeNumber;
	}

	/**
	 * @param officeNumber	ASSIGNS AN OFFICE NUMBER
	 */
	public void setOfficeNumber(int officeNumber) {
		if(officeNumber > 0) {
			this.officeNumber = officeNumber;
		}
	}
	
	

	/**
	 * @return	RETURNS THE PASSWORD OF THE USER
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password	ASSIGNS A PASSWORD TO THE USER
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return array GIVES THE ARRAY WITH THE PEOPLE WHO IS IN CONTACTO WITH THIS WORKER
	 */
	public ArrayList<Person> getWorkersArray() {
		return Array;
	}
	/**
	 */
	public void setPeopleToWorkersArray(Person person) {
		 Array.add(person);
	}

	/**
	 * @return	RETURNS ALL THE ATRIBUTES OF THIS CLASS INTO A STRING
	 */
	public String getSurnameI() {
		return String.valueOf(Character.toUpperCase(surname.charAt(0)));
	}
	/**
	 * DISPLAYS THE WORKERS ATTRIBUTES
	 */
	@Override
	public String toString() {
		return "This worker is called " + name + " " + surname + " (" + this.getSurnameI() + ") " + " and the email provided is the following one: " + email + ". The office assigned is " + officeNumber + " and he/she has " + hoursPerWeek + " hours per week.";
	}
}
