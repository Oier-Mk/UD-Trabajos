package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Training;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.strava.server.services.HomeAppService;
import es.deusto.ingenieria.sd.strava.server.services.LoginAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>(); //los que ya se han logueado

	private LoginAppService loginService;
	private HomeAppService homeService;

	public RemoteFacade(String[] args) throws RemoteException{
		loginService = LoginAppService.getInstance(args);
		homeService = HomeAppService.getInstance(args);
		}

	@Override
	public boolean check(int i,String email, String password) throws Exception {
		System.out.println(" - Checking SN account");
		return loginService.check(i, email, password);
	}

	@Override
	public synchronized long login(int i, String email, String password) {
		try {
			System.out.println(" * RemoteFacade login(): " + email + " / " + password + " / " + i);

			//Perform login() using LoginAppService
			User user = loginService.login(i, email, password); 
						
			//If login() success user is stored in the Server State
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();	
				this.serverState.put(token, user);
				System.out.println("login "+serverState.get(token));
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return -6;
		}
	}

	@Override
	public long register(int i, String mail, String name, String password, Date bday, double weight, int height,
			int hrMax, int hrMin) throws RemoteException {
		System.out.println(" * RemoteFacade register(): " + mail + " / " + password + " / " + i);
		//Perform login() using LoginAppService
		User user = loginService.register(i, mail, name, password, bday, weight, height, hrMax, hrMin); 
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();	
				this.serverState.put(token, user);
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			RemoteException e = new RemoteException("Singup fails!");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);

		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}

	@Override
	public boolean createTrainingSession(long token, String title, String sport, double distance, Date date, int hours, int minutes,
			double duration) throws RemoteException {
		System.out.println(" * RemoteFacade create training session : " + title + " sport " + sport + " distance " + distance + " date " + date + " duration " + duration);

		if (this.serverState.containsKey(token)) {						
			//Make the bid using Bid Application Service
			if (homeService.createTrainingSession(this.serverState.get(token), title, sport, distance, date, hours, minutes, duration)) {
				return true;
			} else {
				throw new RemoteException("createTrainingSession() fails!");
			}
		} else {
			throw new RemoteException("To create a session you must first log in");
		}		
	}

	@Override
	public boolean setupChallenge(long token, String name, Date start, Date end, double distance, double duration,
			String sport) throws RemoteException {
		System.out.println(" * RemoteFacade create set up challenge : " + name + " starting date " + start + " ending date " + end + " distance " + distance + " duration " + duration + " sport " + sport);

		if (this.serverState.containsKey(token)) {						
			//Make the bid using Bid Application Service
			if (homeService.setupChallenge(this.serverState.get(token), name, start, end, distance, duration, sport)) {
				return true;
			} else {
				throw new RemoteException("setupChallenge() fails!");
			}
		} else {
			throw new RemoteException("To create a challenge you must first log in");
		}
	}

	@Override
	public List<ChallengeDTO> getAllChallenges() throws RemoteException {
		System.out.println(" * RemoteFacade get challenges");

		//Get Articles using HomeAppService
		List<Challenge> challenges = homeService.getAllChallenges();

		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getArticles() fails!");
		}
	}

	@Override
	public List<TrainingDTO> getAllTrainings() throws RemoteException {
		System.out.println(" * RemoteFacade get challenges");

		//Get Articles using HomeAppService
		List<Training> trainings = homeService.getAllTrainings();

		if (trainings != null) {
			//Convert domain object to DTO
			return TrainingAssembler.getInstance().trainingToDTO(trainings);
		} else {
			throw new RemoteException("getArticles() fails!");
		}
	}

	@Override
	public UserDTO getUser(long token) throws Exception {
		System.out.println(" * RemoteFacade get user");
		
		System.out.println("getuser   "+serverState.get(token));
		
		User user = serverState.get(token);
		
		if (user != null) {
			//Convert domain object to DTO
			UserDTO u = UserAssembler.getInstance().userToDTO(user);
			System.out.println(u);
			return u;
		} else {
			(new Exception("getUser() fails!")).printStackTrace();
			throw new Exception("getUser() fails!");
		}
	}
}

