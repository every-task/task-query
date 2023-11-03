package com.playdata.taskquery.controller;

import com.playdata.taskquery.domain.task.response.TaskResponse;
import com.playdata.taskquery.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/story/{storyId}")
    public List<TaskResponse> getByStoryId(@PathVariable("storyId") Long id){
        return taskService.getByStoryId(id);
    }
}

