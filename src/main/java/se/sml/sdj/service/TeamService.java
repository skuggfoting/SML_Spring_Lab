package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		if (team.getUsers().size() <= 10) {
			try {
				return repository.save(team);
			}
			catch (DataIntegrityViolationException e) {
				throw new ServiceException("A User can only be in one Team at a time");
			}
		}
		else {
			throw new ServiceException("There can be only 10 Users in each Team");
		}
	}
}
