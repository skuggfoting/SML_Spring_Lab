package se.sml.sdj.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class WorkItemService {

	
	
	// @Autowired
	private WorkItemRepository workItemRepository;
	private TeamRepository teamRepository;

	@Autowired
	public WorkItemService(WorkItemRepository repository) {
		this.workItemRepository = repository;
	}

	public WorkItem save(WorkItem workItem) throws ServiceException {
		{
			if (workItem.getStatus() == "Unstarted" || workItem.getStatus() == "Started" || workItem.getStatus() == "Done" || workItem.getStatus() == "Inactive") {
				return workItemRepository.save(workItem);
			}
			else {
				throw new ServiceException("Status must be 'Unstarted', 'Started', 'Done' or 'Inactive'");
			}
		}
	}
	
	public List<WorkItem> findByStatus(String lable){
		List<WorkItem> workItems = workItemRepository.findByStatus(lable);
		return workItems;
	}

	public List<WorkItem> findByDescriptionContaining(String value){
		List<WorkItem> workItems = workItemRepository.findByDescriptionContaining(value);
		return workItems;
	}
	
	public Collection<WorkItem> findWorkItemsByTeam(String name) {
		Collection<User> users = teamRepository.findUsersByTeam(name);
		Collection<WorkItem> workItems = new ArrayList<>();
		users.forEach(u -> workItems.addAll(u.getWorkItem()));
		return workItems;
	}

//	public Long countByLable(String lable){
//		Long number = repository.countByLable(lable);
//		return number;
//	}
//
////	@Transactional
//	public List<WorkItem> removeByLable(String lable){
//		List<WorkItem> workItems = repository.removeByLable(lable);
//		return workItems;
//	}
}


















