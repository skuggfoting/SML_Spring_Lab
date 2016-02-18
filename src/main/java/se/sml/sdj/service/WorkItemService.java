package se.sml.sdj.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sml.sdj.model.Issue;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.exception.ServiceException;

@Service
public class WorkItemService {

	private WorkItemRepository workItemRepository;
	private TeamRepository teamRepository;

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository, TeamRepository teamRepository) {
		this.workItemRepository = workItemRepository;
		this.teamRepository = teamRepository;
	}

	public WorkItemService save(WorkItem workItem) throws ServiceException {
		if (workItem.getStatus() == "Unstarted" || workItem.getStatus() == "Started" || workItem.getStatus() == "Done" || workItem.getStatus() == "Inactive") {
			workItemRepository.save(workItem);
			return this;
		}
		else {
			throw new ServiceException("Status must be 'Unstarted', 'Started', 'Done' or 'Inactive'");
		}
	}

	//// VARFÖR DENNA?!!!
	public WorkItemService updateStatus(String workItemNumber, String status) throws ServiceException {
		WorkItem workItem = workItemRepository.findByWorkItemNumber(workItemNumber);
		workItem.setStatus(status);
		return save(workItem);
	}
	///////////

	public WorkItemService addIssue(WorkItem workItem, Issue issue) throws ServiceException {
		if (workItem.getStatus() == "Done") {
			workItem.addIssue(issue);
			workItem.setStatus("Unstarted");
			return save(workItem);
		}
		else {
			throw new ServiceException("Can only add an issue when the work item is done ");
		}
	}

	public WorkItemService updateIssue(WorkItem workItem, Issue issue) throws ServiceException {
		return save(workItem);
	}

	public Collection<WorkItem> findByStatus(String lable) {
		return workItemRepository.findByStatus(lable);
	}

	public Collection<WorkItem> findByDescriptionContaining(String value) {
		return workItemRepository.findByDescriptionContaining(value);
	}
	
	public WorkItem findByWorkItemNumber(String workItemNumber) {
		return workItemRepository.findByWorkItemNumber(workItemNumber);
	}
	
	public Collection<WorkItem> findAllByIssue() {
		return workItemRepository.getByIssueNotNull();
	}

	public Collection<WorkItem> findWorkItemsByTeam(String name) {
		Collection<WorkItem> workItems = new ArrayList<>();
		teamRepository.findUsersByTeam(name).forEach(u -> workItems.addAll(u.getWorkItem()));
		return workItems;
	}
}
