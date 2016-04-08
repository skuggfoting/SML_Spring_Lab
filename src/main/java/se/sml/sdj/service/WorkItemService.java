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
		if (workItem.getStatus().equals("Unstarted") || workItem.getStatus().equals("Started") || workItem.getStatus().equals("Done") || workItem.getStatus().equals("Inactive")) {
			workItemRepository.save(workItem);
			return this;
		}
		else {
			throw new ServiceException("Status must be 'Unstarted', 'Started', 'Done' or 'Inactive'");
		}
	}

	public WorkItemService addIssue(String workItemNumber, Issue issue) throws ServiceException {
		
		WorkItem workItem = workItemRepository.getByWorkItemNumber(workItemNumber);
		
		if (workItem.getStatus().equals("Done")) {
			workItem.addIssue(issue);
			workItem.setStatus("Unstarted");
			return save(workItem);
		}
		else {
			throw new ServiceException("Can only add an issue when the work item is done ");
		}
	}

	public WorkItemService updateIssue(WorkItem workItem, Issue issue) throws ServiceException {
		workItem.addIssue(issue);
		return save(workItem);
	}

	public Collection<WorkItem> getByStatus(String lable) {
		return workItemRepository.getByStatus(lable);
	}

	public Collection<WorkItem> getByDescriptionContaining(String text) {
		return workItemRepository.getByDescriptionContaining(text);
	}
	
	public WorkItem getByWorkItemNumber(String workItemNumber) {
		return workItemRepository.getByWorkItemNumber(workItemNumber);
	}
	
	public Collection<WorkItem> getAllByIssue() {
		return workItemRepository.getByIssueNotNull();
	}

	public Collection<WorkItem> getByTeam(String name) {
		Collection<WorkItem> workItems = new ArrayList<>();
		teamRepository.findUsersByTeam(name).forEach(u -> workItems.addAll(u.getWorkItem()));
		return workItems;
	}
}
