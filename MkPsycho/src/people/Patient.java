package people;


@SuppressWarnings("serial")
public class Patient extends Person {

	protected int id;
	
	public Patient(int id, String name, String surname, String email, String image, String button) {
		super(name, surname, email, image, button);
		setId(id);
	}

	/**
	 * @return	RETURN THE ID OF THE PATIENT
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id ASSIGNS AN ID TO THE PATIENT
	 */
	public void setId(int id) {
		if(id > 0) {
			this.id = id;
		}
	}
	/**
	 *	RETURNS ALL THE ATRIBUTES OF THIS CLASS INTO A STRING
	 */
	@Override
	public String toString() {
		return "The patient " + id + " is called " + name + " " + surname + " and the email provided is the following one: " + email + ".";
	}

}
