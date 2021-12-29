package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable="true")
public class Challenge {
	private String name;
	private Date start;
	private Date end;
	private double distance;
	private double duration;
	private String sport;
	
	@Join
	@Persistent(defaultFetchGroup="true")
	private List<User> users = new ArrayList<>();
    
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	@Override
	public String toString() {
		return "Challenge [name=" + name + ", start=" + start + ", end=" + end + ", distance=" + distance
				+ ", duration=" + duration + ", sport=" + sport + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(distance, duration, end, name, sport, start);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		return Double.doubleToLongBits(distance) == Double.doubleToLongBits(other.distance)
				&& Double.doubleToLongBits(duration) == Double.doubleToLongBits(other.duration)
				&& Objects.equals(end, other.end) && Objects.equals(name, other.name)
				&& Objects.equals(sport, other.sport) && Objects.equals(start, other.start);
	}

	
	
	public Challenge(String name, Date start, Date end, double distance, double duration, String sport ) {
			setName(name);
			setStart(start);
			setEnd(end);
			setDistance(distance);
			setDuration(duration);
			setSport(sport);
	}

	public void addUsers(User user) {
		this.users.add(user);
	}	

}