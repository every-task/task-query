package com.playdata.taskquery.service;

import com.playdata.taskquery.domain.suggest.entity.Suggest;
import com.playdata.taskquery.domain.suggest.repository.SuggestRepository;
import com.playdata.taskquery.domain.task.entity.Task;
import com.playdata.taskquery.domain.task.repository.TaskRepository;
import com.playdata.taskquery.domain.task.response.TaskResponse;
import com.playdata.taskquery.kafka.data.StoryKafkaData;
import com.playdata.taskquery.kafka.data.SuggestKafkaData;
import com.playdata.taskquery.kafka.data.TaskKafkaData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SuggestRepository suggestRepository;

    public List<TaskResponse> getRandomTasksBySize(int size) {
        return taskRepository.findRandomTasksByCount(size).stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    public List<TaskResponse> getTasksByStoryId(Long id){
        return taskRepository.findByStoryId(id).stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    public List<TaskResponse> getSuggestTasksByQuestionId(Long id){
        return suggestRepository.findByQuestionIdFetchTask(id).stream()
                .map(Suggest::getTask)
                .map(TaskResponse::fromEntity)
                .toList();
    }

    public void taskRegister(StoryKafkaData data){
        List<Task> tasks = data.tasks().stream()
                .map(task -> Task.createTask(
                        task.id(),
                        data.id(),
                        task.period(),
                        task.content()))
                .toList();

        taskRepository.saveAll(tasks);
    }

    public void suggestRegister(SuggestKafkaData data){
        List<Suggest> suggests = data.taskIds().stream()
                .map(taskId -> Suggest.createSuggest(
                        data.questionId(),
                        Task.fromId(taskId)))
                .toList();
        suggestRepository.saveAll(suggests);
    }

    @Transactional
    public void editTask(TaskKafkaData data) {
        Task task = findById(data.id());
        task.edit(data.period(), data.content());
    }

    private Task findById(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseGet(()-> taskRepository.save(Task.fromId(taskId)));
    }
}
