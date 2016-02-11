package se.sml.sdj.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class WorkItemService {

	
	
	// @Autowired
	private WorkItemRepository workItemRepository;
	private TeamRepository teamRepository;

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository, TeamRepository teamRepository) {
		this.workItemRepository = workItemRepository;
		this.teamRepository = teamRepository;
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
	
	public Collection<WorkItem> findByStatus(String lable){
		return workItemRepository.findByStatus(lable);
	}

	public Collection<WorkItem> findByDescriptionContaining(String value){
		return workItemRepository.findByDescriptionContaining(value);
	}
	
	public Collection<WorkItem> findWorkItemsByTeam(String name) {
		Collection<WorkItem> workItems = new ArrayList<>();
		teamRepository.findUsersByTeam(name).forEach(u -> workItems.addAll(u.getWorkItem()));
		return workItems;
	}
}


















