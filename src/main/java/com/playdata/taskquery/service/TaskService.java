package com.playdata.taskquery.service;

import com.playdata.taskquery.domain.task.entity.Task;
import com.playdata.taskquery.domain.task.repository.TaskRepository;
import com.playdata.taskquery.kafka.data.StoryKafkaData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void register(StoryKafkaData data){
        List<Task> tasks = data.tasks().stream()
                .map(task -> Task.createTask(
                        task.id(),
                        data.id(),
                        task.period(),
                        task.content()))
                .toList();

        taskRepository.saveAll(tasks);
    }
}
