package se.sml.sdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
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

	public Team findByName(String name) {
		Team team = repository.findByName(name);
		return team;
	}

	public List<Team> findAll() {
		List<Team> team = repository.findAll();
		return team;
	}

	public Long countByName(String name) {
		Long number = repository.countByName(name);
		return number;
	}

	public List<User> findUsersByTeam(String name) {
		List<User> users = repository.findUsersByTeam(name);
		return users;
	}
}
