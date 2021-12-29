package es.deusto.ingenieria.sd.strava.client.gui;

import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.controller.HomeController;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;


public class HomeWindow {
	
	private HomeController controller;
		
	public HomeWindow(LoginDialog l, HomeController hc) {
		this.controller = hc;
	}

	public boolean createTrainingSession(long token, String title, String sport, double distance, Date date, int hours, int minutes, double duration) {
		System.out.println(" - Creating training session:  " + title + " for " + sport + " (" + distance + "km) on " + date.toString() + " (" + duration + " hours)" );
		boolean result = this.controller.createTrainingSession(token,title,sport,distance,date,hours,minutes,duration);
		System.out.println("\t* Create session result: " + result);
		return result; 
	}

	public boolean setupChallenge(long token, String name, Date start, Date end, double distance, double duration, String sport) {
		System.out.println(" - Set up challenges:  " + name + " from " + start.toString() + " to " + end.toString() + " (" + distance + "km) on " + " (" + duration + " hours) " + sport  );
		boolean result = this.controller.setupChallenge(token,name,start,end,distance,duration,sport);
		System.out.println("\t* Set up challenge result: " + result);
		return result; 
	}
	
	public UserDTO getUser(long token) {
		System.out.println(" - Getting the user ...");
		UserDTO user = this.controller.getUser(token);
		return user;
	}
	
	public List<ChallengeDTO> getAllChallenges() {
		System.out.println(" - Getting all the challenges ...");
		List<ChallengeDTO> challenges = this.controller.getAllChallenges();
		for (ChallengeDTO challenge : challenges) {System.out.println("\t* " + challenge.getName());		}
		return challenges;
	}
	
	public List<TrainingDTO> getAllTrainings() {
		System.out.println(" - Getting all the sessions ...");
		List<TrainingDTO> trainings = this.controller.getAllTrainings();
		for (TrainingDTO training : trainings) {System.out.println("\t* " + training.getTitle());		}
		return trainings;
	}

}