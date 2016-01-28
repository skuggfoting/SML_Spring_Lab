package se.sml.sdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.WorkItem;
import se.sml.sdj.repository.WorkItemRepository;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class WorkItemService {

	// @Autowired
	private WorkItemRepository repository;

	@Autowired
	public WorkItemService(WorkItemRepository repository) {
		this.repository = repository;
	}

	public WorkItem save(WorkItem workItem) throws ServiceException {
		{
			if (workItem.getStatus() == "Unstarted" || workItem.getStatus() == "Started" || workItem.getStatus() == "Done" || workItem.getStatus() == "Inactive") {
				return repository.save(workItem);
			}
			else {
				throw new ServiceException("Status must be 'Unstarted', 'Started', 'Done' or 'Inactive'");
			}
		}
	}
}