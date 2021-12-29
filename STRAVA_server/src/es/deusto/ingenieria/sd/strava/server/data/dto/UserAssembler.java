package es.deusto.ingenieria.sd.strava.server.data.dto;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class UserAssembler {
	private static UserAssembler instance;
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}

	public UserDTO userToDTO(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setMail(user.getMail());
		dto.setName(user.getName());
		dto.setBday(user.getBday());
		dto.setWeight(user.getWeight());
		dto.setHeight(user.getHeight());
		dto.setHrMax(user.getHrMax());
		dto.setHrMin(user.getHrMin());
		dto.setSessions(TrainingAssembler.getInstance().trainingToDTO(user.getSessions()));
		dto.setChallenges(ChallengeAssembler.getInstance().challengeToDTO(user.getChallenges()));
		
		return dto;
	}

}