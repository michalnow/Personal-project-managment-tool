package pl.michal.exceptions;

public class ProjectIdExceptionResponse {
	private String projectIdentifer;

	public ProjectIdExceptionResponse(String projectIdentifer) {
		this.projectIdentifer = projectIdentifer;
	}

	public String getProjectIdentifer() {
		return projectIdentifer;
	}

	public void setProjectIdentifer(String projectIdentifer) {
		this.projectIdentifer = projectIdentifer;
	}
	
	
}
