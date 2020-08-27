package com.sabrinafz.tasklistmanagerapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabrinafz.tasklistmanagerapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}

