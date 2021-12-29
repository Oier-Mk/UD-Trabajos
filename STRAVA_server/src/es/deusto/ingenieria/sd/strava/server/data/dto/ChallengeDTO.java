package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//This class implements DTO pattern
public class ChallengeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Date start;
	private Date end;
	private double distance;
	private double duration;
	private String sport;

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
		ChallengeDTO other = (ChallengeDTO) obj;
		return Double.doubleToLongBits(distance) == Double.doubleToLongBits(other.distance)
				&& Double.doubleToLongBits(duration) == Double.doubleToLongBits(other.duration)
				&& Objects.equals(end, other.end) && Objects.equals(name, other.name)
				&& Objects.equals(sport, other.sport) && Objects.equals(start, other.start);
	}

	@Override
	public String toString() {
		return "Name=" + name + "\n start=" + start + "\n end=" + end + "\n distance=" + distance
				+ "\n duration=" + duration + "\n sport=" + sport + "]";
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
}