package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Training;

//This class implements DTO pattern
public class UserDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;	
	
	private String mail;
	private String name;
	private Date bday;
	private double weight;
	private int height;
	private int hrMax;
	private int hrMin;
	private List<TrainingDTO> sessions = new ArrayList<>();
	private List<ChallengeDTO> challenges = new ArrayList<>();
	
	public void saveSession(TrainingDTO session) {
		if (session != null) {
			this.sessions.add(session);
		}
	}
	
	public List<ChallengeDTO> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<ChallengeDTO> challenges) {
		this.challenges = challenges;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHrMax() {
		return hrMax;
	}

	public void setHrMax(int hrMax) {
		this.hrMax = hrMax;
	}

	public int getHrMin() {
		return hrMin;
	}

	public void setHrMin(int hrMin) {
		this.hrMin = hrMin;
	}

	public void setSessions(List<TrainingDTO> sessions) {
		this.sessions = sessions;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TrainingDTO> getSessions() {
		return sessions;
	}

	@Override
	public String toString() {
		System.out.println("UserDTO [mail=" + mail + ", name=" + name + ", bday=" + bday + ", weight=" + weight + ", height="
				+ height + ", hrMax=" + hrMax + ", hrMin=" + hrMin + ", sessions=");
		for(int i = 0;i<sessions.size();i++) {
			System.out.println(sessions.toString());
		}
		System.out.println(", challenges=\n");
		for(int i = 0;i<challenges.size();i++) {
			System.out.println(challenges.toString());
		}				
		return "tostring";
	}

	

}