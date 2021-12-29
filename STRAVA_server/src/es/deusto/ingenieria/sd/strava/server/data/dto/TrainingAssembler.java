package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Training;

//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class TrainingAssembler {
	private static TrainingAssembler instance;
	
	public static TrainingAssembler getInstance() {
		if (instance == null) {
			instance = new TrainingAssembler();
		}
		
		return instance;
	}

	public TrainingDTO trainingToDTO(Training session) {
		TrainingDTO dto = new TrainingDTO();
		dto.setTitle(session.getTitle());
		dto.setSport(session.getSport());
		dto.setDistance(session.getDistance());
		dto.setDate(session.getDate());
		dto.setHours(session.getH());
		dto.setMinutes(session.getM());
		dto.setDuration(session.getDuration());
		return dto;
	}

	public List<TrainingDTO> trainingToDTO(List<Training> sessions) {		
		List<TrainingDTO> dtos = new ArrayList<>();
		
		for (Training session : sessions) {
			dtos.add(this.trainingToDTO(session));
		}
		
		return dtos;
	}
}