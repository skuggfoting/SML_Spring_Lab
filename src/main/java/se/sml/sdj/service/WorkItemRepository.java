package se.sml.sdj.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.WorkItem;

interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

	List<WorkItem> findByStatus(String lable);

	List<WorkItem> findByDescriptionContaining(String value);

	WorkItem findByWorkItemNumber(String workItemNumber);

	List<WorkItem> getByIssueNotNull();
	
	Long countByLable(String lable);
}
