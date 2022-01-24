package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.TrainingDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Training;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateway.mail.MailSender;

@SuppressWarnings("unused")
public class HomeAppService {
	private static HomeAppService instance;
	private String[] args;

	public HomeAppService(String[] args) {
		this.args = args;
	}

	public boolean createTrainingSession(User user, String title, String sport, double distance, Date date, int h, int m,
			double duration) {
		try{
			Training t = new Training(title, sport, distance, date, h, m, duration);
			user.saveSession(t);
			t.addUsers(user);
			TrainingDAO.getInstance().save(t);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean setupChallenge(User user, String name, Date start, Date end, double distance, double duration,
			String sport) {
		try{
			Challenge c = new Challenge(name, start, end, distance, duration, sport);
			user.addChallenge(c);
			c.addUsers(user);
			ChallengeDAO.getInstance().save(c);
			//TODO 
			MailSender.getInstance().sendMessage(user.getMail(),"Your challenge: \n"+c.toString()+"\nWas created and stored ir our server.");
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Challenge> getAllChallenges() {
		return ChallengeDAO.getInstance().getAll();
	}

	public List<Training> getAllTrainings() {
		return TrainingDAO.getInstance().getAll();
	}

	public static HomeAppService getInstance(String[] args) {
		return new HomeAppService(args);
	}


}