package com.cauadev.desafiosuperobackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cauadev.desafiosuperobackend.enums.Stage;
import com.cauadev.desafiosuperobackend.model.Task;
import com.cauadev.desafiosuperobackend.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return ResponseEntity.ok(repository.save(task));
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTask(){
		List<Task> task_list = repository.findAll();
		
		if(task_list.isEmpty()) {
			return ResponseEntity.ok(task_list);
		}else{
			task_list.forEach(task ->{
				task.add(WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder
						.methodOn(TaskController.class)
						.getTask(task.getId()))
						.withSelfRel());
			});
		}
		return ResponseEntity.ok(task_list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable Long id){
		Optional<Task> optional = repository.findById(id);
		
		Task task = optional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
			task.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class)
					.getAllTask())
					.withRel("Task List"));
			return ResponseEntity.ok().body(task);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.ok("success");
	}
	
	@PutMapping
	public ResponseEntity<Task> updateTask(@RequestBody Task task){
		return ResponseEntity.ok().body(repository.save(task));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Task> updateStage(@PathVariable Long id){
		Task task = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
		if(task.getStage() == Stage.COMPLETED) {
			task.setStage(Stage.NO_COMPLETED);
		}else task.setStage(Stage.COMPLETED);
		return ResponseEntity.ok(task);
	}

}
