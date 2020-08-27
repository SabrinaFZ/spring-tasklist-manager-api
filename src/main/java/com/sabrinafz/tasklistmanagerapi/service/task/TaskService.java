package com.sabrinafz.tasklistmanagerapi.service.task;

import java.util.List;
import java.util.Optional;

import com.sabrinafz.tasklistmanagerapi.entity.task.Task;
import com.sabrinafz.tasklistmanagerapi.entity.user.User;

public interface TaskService {

	public List<Task> getTasks();

	public Task addTask(Task task);

	public Task getTaskById(int id);
	
	public Task updateTask(int id, Task task);

	public void deleteTask(int id);

}
