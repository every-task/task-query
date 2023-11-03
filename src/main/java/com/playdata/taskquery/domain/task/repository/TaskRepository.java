package com.playdata.taskquery.domain.task.repository;

import com.playdata.taskquery.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByStoryId(Long id);
}
