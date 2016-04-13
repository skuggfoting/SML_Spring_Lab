package se.sml.sdj.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class UserService {

	private UserRepository userRepository;
	private TeamRepository teamRepository;
	private WorkItemRepository workItemRepository;

	@Autowired
	public UserService(UserRepository userRepository, TeamRepository teamRepository, WorkItemRepository workItemRepository) {
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
		this.workItemRepository = workItemRepository;
	}

	@Transactional
	public UserService save(User user) throws ServiceException {
		{
			if (maximumWorkItems(user) && correctUsername(user) && user.getStatus().equals("Active") || user.getStatus().equals("Inactive")) {

				if (user.getStatus().equals("Inactive")) {
					user.getWorkItem().forEach(w -> w.setStatus("Unstarted"));
					workItemRepository.save(user.getWorkItem());
					user.getWorkItem().clear();
				}
				userRepository.save(user);
				return this;
			}
			else {
				throw new ServiceException("Status must be 'Active' or 'Inactive'");
			}
		}
	}

	private boolean correctUsername(User user) throws ServiceException {
		if (user.getUsername().length() >= 10) {
			return true;
		}
		else {
			throw new ServiceException("Username must be at least 10 char long");
		}
	}

	private boolean maximumWorkItems(User user) throws ServiceException {
		if (user.getWorkItem().size() <= 5) {
			return true;
		}
		else {
			throw new ServiceException("Max 5 WorkItem per User");
		}
	}

	public UserService addWorkItem(User user, WorkItem workItem) throws ServiceException {
		
		if (user.getStatus().equals(("Active"))) {
			user.addWorkItem(workItem);
			return save(user);
		}
		else {
			throw new ServiceException("Can't add WorkItem to User with status 'Inactive'");
		}
	}

	public Collection<User> getByFirstName(String firstName) {
		return userRepository.getByFirstName(firstName);
	}

	public Collection<User> getByLastName(String lastName) {
		return userRepository.getByLastName(lastName);
	}

	public User getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

	public Collection<User> getByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.getByFirstNameAndLastName(firstName, lastName);
	}

	public Collection<User> getByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username) {
		return userRepository.getByFirstNameAndLastNameAndUsername(firstName, lastName, username);
	}

	public User getByUserNumber(String userNumber) {
		return userRepository.getByUserNumber(userNumber);
	}

	public Collection<WorkItem> getWorkItemsByUser(String username) {
		return userRepository.getWorkItemsByUser(username);
	}

	public List<User> getUsersByTeam(String team) {
		return teamRepository.getUsersByTeam(team);
	}
}