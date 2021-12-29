package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class User {	
	@PrimaryKey
	private String mail;
	private String name;
	private String password;
	private Date bday;
	private double weight;
	private int height;
	private int hrMax;
	private int hrMin;
	
	@Join
	@Persistent(defaultFetchGroup="true")
	private List<Training> sessions = new ArrayList<>();
	
	@Join
	@Persistent(defaultFetchGroup="true")
	private List<Challenge> challenges = new ArrayList<>();

	public boolean checkPassword(String password) { 
		return this.password.equals(password);
	}	
	
	public void saveSession(Training session) {
		if (session != null) {
			this.sessions.add(session);
		}
	}
	
	public void addChallenge(Challenge challenge) {
		if (challenge != null) {
			this.challenges.add(challenge);
		}		
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

	public List<Training> getSessions() {
		return sessions;
	}

	public void setSessions(List<Training> sessions) {
		this.sessions = sessions;
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bday, challenges, height, hrMax, hrMin, mail, name, password,
				sessions, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(bday, other.bday)
				&& Objects.equals(challenges, other.challenges) && height == other.height
				&& hrMax == other.hrMax && hrMin == other.hrMin && Objects.equals(mail, other.mail)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(sessions, other.sessions)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

	@Override
	public String toString() {
		return "User [mail=" + mail + ", name=" + name + ", password=" + password + "]";
	}
	
	
	public User(String mail, String name, String password, Date bday, double weight, int height, int hrMax, int hrMin ) {
			setMail(mail);
			setName(name);
			setPassword(password);
			setBday(bday);
			setWeight(weight);
			setHeight(height);
			setHrMax(hrMax);
			setHrMin(hrMin);	
	}	

}