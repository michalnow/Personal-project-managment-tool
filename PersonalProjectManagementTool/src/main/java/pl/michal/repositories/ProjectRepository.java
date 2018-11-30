package pl.michal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.michal.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
}
