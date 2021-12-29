package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
@PersistenceCapable(detachable="true")
public class Training {	
	private String title;
	private String sport;
	private double distance;
	private Date date;
	private int h;
	private int m;
	private double duration;

	@Join
	@Persistent(defaultFetchGroup="true")
	private List<User> users = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	
	
	public Training(String title, String sport, double distance, Date date, int h, int m, double duration ) {
			setTitle(title);
			setSport(sport);
			setDistance(distance);
			setDate(date);
			setH(h);
			setM(m);
			setDuration(duration);
	}	
	
	public void addUsers(User user) {
		this.users.add(user);
	}	

}