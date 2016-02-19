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

	public UserService addWorkItem(String username, WorkItem workItem) throws ServiceException {
		User user = findByUsername(username);
		if (user.getStatus().equals(("Active"))) {
			user.addWorkItem(workItem);
			return save(user);
		}
		else {
			throw new ServiceException("Can't add WorkItem to User with status 'Inactive'");
		}
	}

	public Collection<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	public Collection<User> findByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Collection<User> findByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public Collection<User> findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username) {
		return userRepository.findByFirstNameAndLastNameAndUsername(firstName, lastName, username);
	}

	public User findByUserNumber(String userNumber) {
		return userRepository.findByUserNumber(userNumber);
	}

	public Collection<WorkItem> findWorkItemsByUser(String name) {
		return userRepository.findWorkItemsByUser(name);
	}

	public List<User> findUsersByTeam(String name) {
		return teamRepository.findUsersByTeam(name);
	}
}