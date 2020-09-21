package people;

@SuppressWarnings("serial")
public
class Client extends Person {
	
	protected String enterprise;
	protected String activity;
		
	
	public Client(String name, String surname,String email, String enterprise,String activity, String image, String button) {
		super(name, surname, email, image, button);
		setEnterprise(enterprise);
		setActivity(activity);
		
	}


	/**
	 * @return	RETURNS THE ENTERPRISE OF THE CLIENT
	 */
	public String getEnterprise() {
		return enterprise;
	}


	/**
	 * @param ASSIGNS AN ENTERPRISE OF THE CLIENT
	 */
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}


	/**
	 * @return RETURNS THE ACTIVITY THAT THE CLIENT HAS ASKED
	 */
	public String getActivity() {
		return activity;
	}


	/**
	 * @param activity ASSIGNS THE ACTIVITY THE CLIENT WANTS
	 */
	public void setActivity(String activity) {
		if (activity.equalsIgnoreCase("conference")) {	
			this.activity = " a conference";
		}
		else if (activity.equalsIgnoreCase("interview")) {
			this.activity = " an interview";
		}
		else {
			this.activity = errorMessage;
		}
	}
	/**
	 *	RETURNS ALL THE ATRIBUTES OF THIS CLASS INTO A STRING
	 */
	@Override
	public String toString() {
		return "This client is called " + name + " " + surname + " and the email provided is the following one: " + email + ". The enterprise he/she comes from is " + enterprise + " and he/she asks for " + activity + ".";
	}
}
