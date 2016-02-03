package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.Team;
import se.sml.sdj.repository.TeamRepository;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class TeamService {

	// @Autowired
	private TeamRepository repository;

	@Autowired
	public TeamService(TeamRepository repository) {
		this.repository = repository;
	}

	public Team save(Team team) throws ServiceException {
		{
			System.out.println(team.getUsers().size());
			if (team.getUsers().size() <= 10) {
				return repository.save(team);
			}
			else {
				throw new ServiceException("There can be only 10 Users in each Team");
			}
		}
	}
}