package com.sabrinafz.tasklistmanagerapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabrinafz.tasklistmanagerapi.dto.TaskDTO;
import com.sabrinafz.tasklistmanagerapi.dto.UserDTO;
import com.sabrinafz.tasklistmanagerapi.entity.Task;
import com.sabrinafz.tasklistmanagerapi.entity.User;
import com.sabrinafz.tasklistmanagerapi.error.CustomNotFoundException;
import com.sabrinafz.tasklistmanagerapi.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<TaskDTO> getTasks() {
		List<Task> tasks = taskService.getTasks();
		return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@PostMapping
	public TaskDTO addTask(@RequestBody Task task) throws Exception {

		Task newTask;

		try {
			newTask = taskService.addTask(task);
		} catch(Exception exc) {
			throw new Exception(exc.getMessage());
		}

		return convertToDTO(newTask);
	}

	@PutMapping("/{id}")
	public TaskDTO updateUser(@PathVariable int id, @RequestBody Task task) {

		Task tempTask = taskService.getTaskById(id);

		if (tempTask == null) {
			throw new CustomNotFoundException("Task with id " + id + " do not exist");
		}

		tempTask = taskService.updateTask(id, task);
		return convertToDTO(tempTask);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable int id) {

		Task tempTask = taskService.getTaskById(id);

		if (tempTask == null) {
			throw new CustomNotFoundException("Task with id " + id + " do not exist");
		}
		
		taskService.deleteTask(id);

		return new ResponseEntity<>("Task with id " + id + " deleted", HttpStatus.NO_CONTENT);
	}

	private TaskDTO convertToDTO(Task task) {
		return modelMapper.map(task, TaskDTO.class);		
	}

}
