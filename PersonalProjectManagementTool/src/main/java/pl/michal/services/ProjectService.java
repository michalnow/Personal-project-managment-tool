package pl.michal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.michal.entities.Backlog;
import pl.michal.entities.Project;
import pl.michal.exceptions.ProjectIdException;
import pl.michal.repositories.BacklogRepository;
import pl.michal.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	public Project saveOrUpdateProject(Project project) {

		try {
			
			project.setProjectIdentifer(project.getProjectIdentifer().toUpperCase());

			if(project.getId() == null){
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifer().toUpperCase());
			}

			if(project.getId() != null){
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifer().toUpperCase()));
			}

			return projectRepository.save(project);
			
		} catch (Exception e) {
			
			throw new ProjectIdException(
					"Project ID " + project.getProjectIdentifer().toUpperCase() + " already exist");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifer(projectId);
		
		if(project == null) {

			throw new ProjectIdException("Project " + projectId + " does not exist");
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProject(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifer(projectId);
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete project with ID " + projectId + ", this project does not exist");
		}
		
		projectRepository.delete(project);
		
	}
	
	
	
	
	
}
