package com.playdata.taskquery.domain.task.response;

import com.playdata.taskquery.domain.task.entity.Period;
import com.playdata.taskquery.domain.task.entity.Task;

import java.util.UUID;

public record TaskResponse(
        UUID id,
        Period period,
        String content
) {
    public static TaskResponse fromEntity(Task task){
        return new TaskResponse(
                task.getId(),
                task.getPeriod(),
                task.getContent()
        );
    }
}
