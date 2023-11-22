package com.playdata.taskquery.controller;

import com.playdata.taskquery.domain.task.response.TaskResponse;
import com.playdata.taskquery.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/random")
    public List<TaskResponse> getRandomTasksBySize(
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ){
        return taskService.getRandomTasksBySize(size);
    }

    @GetMapping("/stories/{storyId}/tasks")
    public List<TaskResponse> getTasksByStoryId(@PathVariable("storyId") Long id){
        return taskService.getTasksByStoryId(id);
    }

    @GetMapping("/suggest/questions/{questionId}/tasks")
    public List<TaskResponse> getSuggestTasksByQuestionId(@PathVariable("questionId") Long id){
        return taskService.getSuggestTasksByQuestionId(id);
    }
}

