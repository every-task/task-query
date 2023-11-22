package com.playdata.taskquery.domain.task.repository;

import com.playdata.taskquery.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("select t from Task t where t.storyId = :storyId")
    List<Task> findByStoryId(Long storyId);

    @Query(value = "select * from task order by rand() limit :size", nativeQuery = true)
    List<Task> findRandomTasksByCount(int size);
}
