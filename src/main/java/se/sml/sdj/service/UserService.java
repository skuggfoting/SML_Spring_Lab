package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.User;
import se.sml.sdj.repository.UserRepository;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class UserService {

	// @Autowired - Den ena eller den andra räcker här
	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User save(User user) throws ServiceException {
		{
			if (user.getStatus() == "Active" || user.getStatus() == "Inactive") {
				return repository.save(user);
			}
			else {
				throw new ServiceException("Status must be 'Active' or 'Inactive'");
			}
		}

	}
}