package program;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.JButton;
import appointments.Appointment;
import appointments.Day;
import people.Client;
import people.Patient;
import people.Person;
import people.Worker;
import windows.AddAppointment;


/**
 * writeS ALL THE DATA OF THE PROGRAM
 *
 */
public class ContainingClass{


	public TreeMap< Integer, Patient> Patients;
	public TreeMap< String, Worker> Workers;
	public TreeMap< String, Client> Clients;
	public TreeMap< String,Appointment> Appointments;

	public ContainingClass(){

		Patients = new TreeMap< Integer, Patient>();
		Workers = new TreeMap< String, Worker>();
		Clients = new TreeMap< String, Client>();
		Appointments = new TreeMap< String, Appointment>();

	}

	/**
	 * @return RETURNS THE PATIENTS TREEMAP
	 */
	public TreeMap<Integer, Patient> getPatients() {
		return Patients;
	}

	/**
	 * @param patients ASSIGNS THE PATIENTS TREEMAP
	 */
	public void setPatients(TreeMap<Integer, Patient> patients) {
		Patients = patients;
	}

	/**
	 * @param patient ADDS A PATIENT TO THE TREEMAP
	 */
	@SuppressWarnings("deprecation")
	public void putAppointment(JButton b) {
		int day;
		int month;
		int year;
		Person newP = MkPsycho.windowWorker.getWorkersArray().get(Integer.parseInt(b.getName()));
		Date newDate = AddAppointment.newDay.getDay();
		Integer newDateKey =Integer.parseInt(newDate.getYear()+""+(newDate.getMonth()+1)+""+newDate.getDate());

		day = newDate.getDate();
		month = newDate.getMonth()+1;
		year = newDate.getYear();

		Appointment newApp = new Appointment(newP,
				year, month, day, Integer.parseInt(AddAppointment.spinnerHour) , Integer.parseInt(AddAppointment.spinnerMinutes));

		if (AddAppointment.hour1.isSelected()) newApp.setDuration(false);
		if (AddAppointment.hour15.isSelected()) newApp.setDuration(true);
		
		Worker worker = MkPsycho.windowWorker;
		
		TreeMap<Integer, Day> calendar = worker.calendar.getCalendar();
		
		Day appointmentDay = calendar.get(newDateKey);
		
		TreeSet<Appointment> dayAppointments = appointmentDay.getAppointmentsPerDay();
		
		dayAppointments.add(newApp);
		
		Appointments.put(newApp.getDate()+"",newApp);
	}

	/**
	 * @param patient REMOVES A PATIENT FROM THE TREEMAP
	 */
	@SuppressWarnings({ "deprecation" })
	public void removeAppointment(JButton appointment, Appointment cita) {

		TreeMap<Integer, Day> calendar = MkPsycho.windowWorker.calendar.getCalendar();
	
		Date dayToDelete = MkPsycho.actualDay;
		
		if(dayToDelete.getDay()==0){
			dayToDelete = new Date(dayToDelete.getTime() - 6*24*3600000);
		}
		else {
			dayToDelete = new Date(dayToDelete.getTime() - (dayToDelete.getDay()-1)*24*3600000);
		}
		
		
		Integer dayKey = null;

		if(appointment.getParent().getName().contentEquals("day1center")) {
			
			dayToDelete = new Date (dayToDelete.getTime() + 24*3600000*0);
			
			dayKey = Integer.parseInt(dayToDelete.getYear()+""+(dayToDelete.getMonth()+1)+""+dayToDelete.getDate());
			
		}
		if(appointment.getParent().getName().contentEquals("day2center")) {

			dayToDelete = new Date (dayToDelete.getTime() + 24*3600000*1);
			
			dayKey = Integer.parseInt(dayToDelete.getYear()+""+(dayToDelete.getMonth()+1)+""+dayToDelete.getDate());
			
		}
		if(appointment.getParent().getName().contentEquals("day3center")) {

			dayToDelete = new Date (dayToDelete.getTime() + 24*3600000*2);
			
			dayKey = Integer.parseInt(dayToDelete.getYear()+""+(dayToDelete.getMonth()+1)+""+dayToDelete.getDate());
			
		}
		if(appointment.getParent().getName().contentEquals("day4center")) {

			dayToDelete = new Date (dayToDelete.getTime() + 24*3600000*3);
			
			dayKey = Integer.parseInt(dayToDelete.getYear()+""+(dayToDelete.getMonth()+1)+""+dayToDelete.getDate());
			
		}
		if(appointment.getParent().getName().contentEquals("day5center")) {

			dayToDelete = new Date (dayToDelete.getTime() + 24*3600000*4);
			
			dayKey = Integer.parseInt(dayToDelete.getYear()+""+(dayToDelete.getMonth()+1)+""+dayToDelete.getDate());
			
		}
		System.out.println(calendar);
		System.out.println(dayKey);
		System.out.println(cita);
		calendar.get(dayKey).getAppointmentsPerDay().remove(cita);
		
	
	}


	/**
	 * @return RETURNS THE APPOINTMENTS OF THE MAP
	 */
	public TreeMap<String,Appointment> getAppointments() {
		return Appointments;
	}

	/**
	 * @param appointments ASSIGNS THE APPOINTMENTS TO THE MAP
	 */
	public void setAppointments(TreeMap<String, Appointment> appointments) {
		Appointments = appointments;
	}

	/**
	 * @param patient ADDS A PATIENT TO THE TREEMAP
	 */
	public void putPatient(Patient patient) {
		Patients.put(patient.getId(), patient) ;
	}

	/**
	 * @param patient REMOVES A PATIENT FROM THE TREEMAP
	 */
	public void removePatient(Patient patient) {

		Patients.remove(patient.getSurname(), patient) ;
	}

	/**
	 * @return RETURNS THE WORKERS TREEMAP
	 */
	public TreeMap<String, Worker> getWorkers() {
		return Workers;
	}

	/**
	 * @param patients ASSIGNS THE WORKERS TREEMAP
	 */
	public void setWorkers(TreeMap<String, Worker> workers) {
		Workers = workers;
	}

	/**
	 * @param patient ADDS A WORKER TO THE TREEMAP
	 */
	public void putWorker(Worker worker) {
		Workers.put(worker.getSurname(), worker) ;
	}

	/**
	 * @param patient REMOVES A WORKER FROM THE TREEMAP
	 */
	public void removeWorker(Worker worker) {

		Workers.remove(worker.getSurname(), worker) ;
	}

	/**
	 * @return RETURNS THE CLIENTS TREEMAP
	 */
	public TreeMap<String, Client> getClients() {
		return Clients;
	}

	/**
	 * @param patients ASSIGNS THE CLIENTS TREEMAP
	 */
	public void setClients(TreeMap<String, Client> clients) {
		Clients = clients;
	}

	/**
	 * @param patient ADDS A CLIENT TO THE TREEMAP
	 */
	public void putClient(Client client) {
		Clients.put(client.getSurname(), client) ;
	}

	/**
	 * @param patient REMOVES A CLIENT FROM THE TREEMAP
	 */
	public void removeClient(Client client) {

		Clients.remove(client.getSurname(), client) ;
	}


	/**
	 * @return RETURNS THE PATIENTS TREEMAP TO THE MAIN CONSOLE
	 */
	public String toStrPatients() {
		String str = "The registered patients are the following ones: \n";
		for(int i: Patients.keySet()) {
			str += Patients.get(i).getName() + " " + Patients.get(i).getSurname() + "\n";
		}
		return str;
	}

	/**
	 * @return RETURNS THE WORKERS TREEMAP TO THE MAIN CONSOLE
	 */
	public String toStrWorkers() {
		String str = "The registered workers are the following ones: \n";
		for(String i: Workers.keySet()) {
			str += Workers.get(i).getName() + " " + Workers.get(i).getSurname() + "\n";
		}
		return str;
	}

	/**
	 * @return RETURNS THE CLIENTS TREEMAP TO THE MAIN CONSOLE
	 */
	public String toStrClients() {
		String str = "The registered clients are the following ones: \n";
		for(String i: Clients.keySet()) {
			str += Clients.get(i).getName() + " " + Clients.get(i).getSurname() + "\n";
		}
		return str;
	}



	/**
	 * @param fileName writeS THE DATA TO THE FILE USED AS PARAMETER
	 */
	public void writeData(String fileName) {
		try { 
			ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(fileName));
			write.writeObject(Patients);
			write.writeObject(Workers);
			write.writeObject(Clients);
			write.writeObject(Appointments);
			write.close();
		}
		catch(Exception e){
			System.out.println("An exception has been founded");
			e.printStackTrace();
		}
	}

	/**
	 * @param fileName READS THE DATA writeD ON THE FILE USED AS PARAMETER
	 */
	@SuppressWarnings("unchecked")
	public void readData(String fileName) {
		try { 
			ObjectInputStream read = new ObjectInputStream(new FileInputStream(fileName));
			Patients = (TreeMap<Integer, Patient>) read.readObject();
			Workers = (TreeMap<String, Worker>) read.readObject();
			Clients = (TreeMap<String, Client>) read.readObject();
			Appointments = (TreeMap<String, Appointment>) read.readObject();
			read.close();
		}
		catch(Exception e){
			System.out.println("An exception has been founded");
			e.getMessage();
			e.printStackTrace();
		}
	}







}
