package com.sabrinafz.tasklistmanagerapi.dao.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabrinafz.tasklistmanagerapi.entity.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}

