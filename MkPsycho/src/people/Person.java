package people;

import java.io.Serializable;

@SuppressWarnings("serial")
public
abstract class Person implements Serializable{
	protected String name;
	protected String surname;
	protected String email;
	protected String image;
	protected String button;
	public String errorMessage = "Error";
	public int errorMessageINT = 0 ;

	

	public Person(String name, String surname, String email, String image, String button) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		this.image = image;
		this.button = button;
	}

	/**
	 * @return	RETURNS THE EMAIL OF THE PERSON
	 */
	public String getEmail() {
		return email;
	}

	
	/**
	 * @param email	ENSURES THAT THE EMAIL CAN BE COMPATIBLE
	 */
	public void setEmail(String email) {
		int arroba = email.indexOf('@');
		int dot = email.indexOf('.');
		if (arroba != -1 && dot != -1) {
			this.email = email;
		}
		else {
			this.email = errorMessage;
		}
	}

	/**
	 * @return	RETURNS THE NAME OF THE PERSON
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name	ASSINGS THE NAME OF THE PERSON 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return RETURNS THE SURNAME OF THE PERSON 
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname ASSINGS THE SURNAME OF THE PERSON
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	} 

	
	/**
	 * @return	RETURNS THE IMAGE ASSIGNED TO THE PERSON 
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image ASSIGNS AN IMAGE TO A PERSON
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return	RETURNS THE BUTTON ASSIGNED TO THE PERSON
	 */
	public String getButton() {
		return button;
	}

	/**
	 * @param button ASSIGNS A BUTTON TO THE PERSON
	 */
	public void setButton(String button) {
		this.button = button;
	}

	/**
	 *	RETURNS ALL THE ATRIBUTES OF THIS CLASS INTO A STRING
	 */
	@Override
	public String toString() {
		return "This person is called " + name + " " + surname + ", and the email provided is the following one: " + email + ".";
	}

}
