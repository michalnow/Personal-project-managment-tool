package pl.michal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.michal.entities.ProjectTask;
import pl.michal.services.MapValidationErrorService;
import pl.michal.services.ProjectTaskService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult bindingResult, @PathVariable String backlog_id){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
        if(errorMap != null){
            return errorMap;
        }

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){

        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{projectTask_id")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String projectTask_id){
        ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlog_id,projectTask_id);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{projectTask_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult,
                                               @PathVariable String backlog_id, @PathVariable String projectTask_id){

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
        if(errorMap != null){
            return errorMap;
        }

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, projectTask_id);

        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{projectTask_id}")
    public ResponseEntity<?> deleteProjectTask( @PathVariable String backlog_id, @PathVariable String projectTask_id){

        projectTaskService.deleteProjectTaskByProjectSequence(backlog_id,projectTask_id);

        return new ResponseEntity<String>("Project task " + projectTask_id + "was deleted successfully", HttpStatus.OK);
    }

}
