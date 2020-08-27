package com.sabrinafz.tasklistmanagerapi.service.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabrinafz.tasklistmanagerapi.dao.task.TaskRepository;
import com.sabrinafz.tasklistmanagerapi.dao.user.UserRepository;
import com.sabrinafz.tasklistmanagerapi.entity.task.Task;
import com.sabrinafz.tasklistmanagerapi.entity.user.User;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task addTask(Task task) {
		
		Task tempTask = new Task();
		tempTask.setTitle(task.getTitle());
		tempTask.setDescription(task.getDescription());
		tempTask.setStatus(task.getStatus());
		tempTask.setPriority(task.getPriority());
		
		User tempUser = getOwner(task);
		tempTask.setOwner(tempUser);
		
		taskRepository.save(tempTask);
		
		return tempTask;
	}

	@Override
	public Task getTaskById(int id) {		
		Optional<Task> tempTask = taskRepository.findById(id);		
		return tempTask.get();
	}

	@Override
	public Task updateTask(int id, Task task) {
		
		Task tempTask = (taskRepository.findById(id)).get();
		
		tempTask.setTitle(task.getTitle() != null ? task.getTitle() : tempTask.getTitle());
		tempTask.setDescription(task.getDescription() != null ? task.getDescription() : tempTask.getDescription());
		tempTask.setStatus(task.getStatus() != null ? task.getStatus() : tempTask.getStatus());
		tempTask.setPriority(task.getPriority() != null ? task.getPriority() : tempTask.getPriority());
					
		tempTask.setOwner(task.getOwner() != null ? getOwner(task) : getOwner(tempTask));
		
		taskRepository.save(tempTask);

		return tempTask;
	}
	
	@Override
	public void deleteTask(int id) {
		
		Task tempTask = (taskRepository.findById(id)).get();		
		taskRepository.delete(tempTask);
		
	}
	
	private User getOwner(Task task) {
		User tempUser = userRepository.findByEmail(task.getOwner().getEmail());
		return tempUser;
	}

}
