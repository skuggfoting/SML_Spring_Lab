package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.sml.sdj.model.User;
import se.sml.sdj.repository.UserRepository;
import se.sml.sdj.repository.WorkItemRepository;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class UserService {

	// @Autowired - Den ena eller den andra räcker här
	private UserRepository userRepository;
	private WorkItemRepository workItemRepository;

	@Autowired
	public UserService(UserRepository userRepository, WorkItemRepository workItemRepository) {
		this.userRepository = userRepository;
		this.workItemRepository = workItemRepository;
	}

	@Transactional
	public User save(User user) throws ServiceException {
		{
			{
				if (maximumWorkItems(user) && correctPassword(user) && user.getStatus() == "Active" || user.getStatus() == "Inactive") {
					
					if (user.getStatus() == "Inactive") {
						user.getWorkItem().forEach(w -> w.setStatus("Unstarted"));
						user.getWorkItem().forEach(w -> workItemRepository.save(w));
						workItemRepository.save(user.getWorkItem());
					}
					return userRepository.save(user);
				}
				else {
					throw new ServiceException("Status must be 'Active' or 'Inactive'");
				}
			}
		}
	}

	private boolean correctPassword(User user) throws ServiceException {
		{
			if (user.getPassword().length() >= 10) {
				return true;
			}
			else {
				throw new ServiceException("Password must be at least 10 char long");
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
}
