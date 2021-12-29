package es.deusto.ingenieria.sd.strava.client.controller;

import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;


//This class implements Controller pattern.
public class HomeController {

	//Reference to the Service Locator
	private ServiceLocator serviceLocator;

	public HomeController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator; 
	}

	public boolean createTrainingSession(long token, String title, String sport, double distance, Date date, int hours, int minutes, double duration) {
		boolean result = false;
		try {
			result = this.serviceLocator.getService().createTrainingSession(token,title,sport,distance,date,hours,minutes,duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}

	public boolean setupChallenge(long token, String name, Date start, Date end, double distance, double duration, String sport) {
		boolean result = false;
		try {
			result = this.serviceLocator.getService().setupChallenge(token,name,start,end,distance,duration,sport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}

	public List<ChallengeDTO> getAllChallenges() {
		try {
			return this.serviceLocator.getService().getAllChallenges();
		} catch (Exception e) {
			System.out.println("# Error getting all challenges: " );e.printStackTrace();
			return null;
		}
	}

	public List<TrainingDTO> getAllTrainings() {
		try {
			return this.serviceLocator.getService().getAllTrainings();
		} catch (Exception e) {
			System.out.println("# Error getting all trainings: ");e.printStackTrace();
			return null;
		}
	}

	public UserDTO getUser(long token) {
		try {
			return this.serviceLocator.getService().getUser(token);
		} catch (Exception e) {
			System.out.println("# Error getting the actual user: ");e.printStackTrace();
			return null;
		}
	}
}