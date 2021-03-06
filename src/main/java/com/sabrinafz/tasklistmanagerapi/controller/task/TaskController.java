package com.sabrinafz.tasklistmanagerapi.controller.task;

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

import com.sabrinafz.tasklistmanagerapi.dto.task.TaskDTO;
import com.sabrinafz.tasklistmanagerapi.dto.user.UserDTO;
import com.sabrinafz.tasklistmanagerapi.entity.task.Task;
import com.sabrinafz.tasklistmanagerapi.entity.user.User;
import com.sabrinafz.tasklistmanagerapi.error.CustomNotFoundException;
import com.sabrinafz.tasklistmanagerapi.service.task.TaskService;

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
	public TaskDTO updateUser(@PathVariable int id, @RequestBody Task task) throws Exception {

		Task tempTask = taskService.getTaskById(id);

		if (tempTask == null) {
			throw new CustomNotFoundException("Task with id " + id + " do not exist");
		}

		try {
			tempTask = taskService.updateTask(id, task);
		} catch(Exception exc) {
			throw new Exception(exc.getMessage());
		}
		
		return convertToDTO(tempTask);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable int id) throws Exception {

		Task tempTask = taskService.getTaskById(id);

		if (tempTask == null) {
			throw new CustomNotFoundException("Task with id " + id + " do not exist");
		}
		
		try {
			taskService.deleteTask(id);
		} catch(Exception exc) {
			throw new Exception(exc.getMessage());
		}

		return new ResponseEntity<>("Task with id " + id + " deleted", HttpStatus.NO_CONTENT);
	}

	private TaskDTO convertToDTO(Task task) {
		return modelMapper.map(task, TaskDTO.class);		
	}

}
