package com.sabrinafz.tasklistmanagerapi.dto;

import com.sabrinafz.tasklistmanagerapi.enums.Priority;
import com.sabrinafz.tasklistmanagerapi.enums.Status;

public class TaskDTO {
	
	private int id;
	private String title;
	private String description;
	private Status status;
	private Priority priority;
	private UserDTO owner;
	
	public TaskDTO() {}
	
	public TaskDTO(int id, String title, String description, Status status, Priority priority, UserDTO owner) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public UserDTO getOwner() {
		return owner;
	}
	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", owner=" + owner + "]";
	}
	
	
}
