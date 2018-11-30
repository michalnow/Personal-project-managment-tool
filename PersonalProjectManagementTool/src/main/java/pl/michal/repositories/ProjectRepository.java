package pl.michal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.michal.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	Project findByProjectIdentifer(String projectId);
	
	@Override
	Iterable<Project> findAll();
}
