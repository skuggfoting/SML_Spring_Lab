package se.sml.sdj.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
	
	List<WorkItem> findByLable(String lable);

	Long countByLable(String lable);

	@Transactional
	List<WorkItem> removeByLable(String lable);
}
