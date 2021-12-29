package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

//This class implements DTO pattern
public class TrainingDTO implements Serializable {	

	private static final long serialVersionUID = 1L;
	private String title;
	private String sport;
	private double distance;
	private Date date;
	private int hours;
	private int minutes;
	private double duration;

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

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, distance, duration, hours, minutes, sport, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainingDTO other = (TrainingDTO) obj;
		return Objects.equals(date, other.date)
				&& Double.doubleToLongBits(distance) == Double.doubleToLongBits(other.distance)
				&& Double.doubleToLongBits(duration) == Double.doubleToLongBits(other.duration) && hours == other.hours
				&& minutes == other.minutes && Objects.equals(sport, other.sport) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Title=" + title + "\n sport=" + sport + "\n distance=" + distance + "\n date=" + date
				+ "\n hours=" + hours + "\n minutes=" + minutes + "\n duration=" + duration + "]";
	}

	

	
}