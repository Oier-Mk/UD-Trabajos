package es.deusto.ingenieria.sd.strava.server.data.dto;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

import java.util.ArrayList;
import java.util.List;


//This class is part of the DTO pattern. It also implements Singleton Pattern.
public class ChallengeAssembler {	
	private static ChallengeAssembler instance;
	
	public static ChallengeAssembler getInstance() {
		if (instance == null) {
			instance = new ChallengeAssembler();
		}

		return instance;
	}

	public ChallengeDTO challengeToDTO(Challenge challenge) {
		ChallengeDTO dto = new ChallengeDTO();
		
		dto.setName(challenge.getName());
		dto.setStart(challenge.getStart());
		dto.setEnd(challenge.getEnd());
		dto.setDistance(challenge.getDistance());
		dto.setDuration(challenge.getDuration());
		dto.setSport(challenge.getSport());
				
		return dto;
	}
	
	public List<ChallengeDTO> challengeToDTO(List<Challenge> challenges) {
		List<ChallengeDTO> dtos = new ArrayList<>();
		
		for (Challenge challenge : challenges) {
			dtos.add(this.challengeToDTO(challenge));
		}
		
		return dtos;		
	}
}