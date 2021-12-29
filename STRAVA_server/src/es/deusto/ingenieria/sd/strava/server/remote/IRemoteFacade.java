package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	
	//log in
	public long login(int i, String email, String password) throws Exception;
	public boolean check(int i, String email, String password) throws RemoteException, Exception;
	public long register(int i,String mail, String name, String password, Date bday, double weight, int height, int hrMax, int hrMin) throws Exception;
	public void logout(long token) throws Exception;

	//home window
	public boolean createTrainingSession(long token, String title, String sport, double distance, Date date, int hours, int minutes, double duration)throws Exception;
	public boolean setupChallenge(long token, String name, Date start, Date end, double distance, double duration, String sport) throws Exception;
	public List<ChallengeDTO> getAllChallenges() throws Exception ;
	public List<TrainingDTO> getAllTrainings() throws Exception ;
	public UserDTO getUser(long token) throws Exception;

}