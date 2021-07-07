package com.cauadev.desafiosuperobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cauadev.desafiosuperobackend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
