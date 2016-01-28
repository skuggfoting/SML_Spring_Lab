package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.Team;
import se.sml.sdj.repository.TeamRepository;

@Service
public class TeamService {

//	@Autowired
	private TeamRepository repository;

	@Autowired
	public TeamService(TeamRepository repository){
		this.repository = repository;
	}
	
	public Team save(Team team){
		return repository.save(team);
	}
}