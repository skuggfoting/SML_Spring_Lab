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

	// @Autowired - Den ena eller den andra räcker här
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
	public User save(User user) throws ServiceException {
		{
			{
				if (maximumWorkItems(user) && correctUsername(user) && user.getStatus() == "Active" || user.getStatus() == "Inactive") {

					if (user.getStatus() == "Inactive") {
						user.getWorkItem().forEach(w -> w.setStatus("Unstarted"));
						workItemRepository.save(user.getWorkItem());
						user.getWorkItem().clear();
						// throw new ServiceException("User is 'Inactive' and
						// all WorkItems associated with the User has been set
						// to 'Unstarted' and dissociated with the User");
					}
					return userRepository.save(user);
				}
				else {
					throw new ServiceException("Status must be 'Active' or 'Inactive'");
				}
			}
		}
	}

	private boolean correctUsername(User user) throws ServiceException {
		{
			if (user.getUsername().length() >= 10) {
				return true;
			}
			else {
				throw new ServiceException("Username must be at least 10 char long");
			}
		}
	}

	private boolean maximumWorkItems(User user) throws ServiceException {
		{
			if (user.getWorkItem().size() <= 5) {
				return true;
			}
			else {
				throw new ServiceException("Max 5 WorkItem per User");
			}
		}
	}

	// Lekstuga  ////////////////////////////////////////////////////////////////////////////////////////////
	
	public void addWorkItem(String username, WorkItem workItem) throws ServiceException {
		User user = userRepository.findByUsername(username);
		if (user.getStatus() == "Active") {
			user.addWorkItem(workItem);
			userRepository.save(user);
		}
		else {
			throw new ServiceException("Can't add WorkItem to User with status 'Inactive'");
		}
	}

	public void addWorkItems(String username, Collection<WorkItem> workItem) throws ServiceException {
		User user = userRepository.findByUsername(username);
		if (user.getStatus() == "Active") {
			workItem.forEach(wi -> user.addWorkItem(wi));
			userRepository.save(user);
		}
		else {
			throw new ServiceException("Can't add WorkItems to User with status 'Inactive'");
		}
	}

	public User updateStatus(String username, String status) {
		User user = userRepository.findByUsername(username);
		user.setStatus(status);
		return save(user);
	}

	// Lekstuga slutar här  /////////////////////////////////////////////////////////////////////////////////

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
