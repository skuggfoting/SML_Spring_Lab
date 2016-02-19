package se.sml.sdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class TeamService {

	private TeamRepository repository;

	@Autowired
	public TeamService(TeamRepository repository) {
		this.repository = repository;
	}

	public TeamService save(Team team) throws ServiceException {
		if (team.getUsers().size() <= 10) {
			if (team.getStatus().equals("Active") || team.getStatus().equals("Inactive")) {
				repository.save(team);
				return this;
			}
			else {
				throw new ServiceException("Status must be 'Active' or 'Inactive'");
			}
		}
		else {
			throw new ServiceException("There can be only 10 Users in each Team");
		}
	}
	
	public TeamService addUser(String name, User user) {
		try {
		Team team =findByName(name);
		team.addUser(user);
		return save(team);
		}
		catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new ServiceException("A User can only be in one Team at a time");
		}
	}

	public Team findByName(String name) {
		return repository.findByName(name);
	}

	public List<Team> findAll() {
		return repository.findAll();
	}

	public Long countByName(String name) {
		return repository.countByName(name);
	}
}










