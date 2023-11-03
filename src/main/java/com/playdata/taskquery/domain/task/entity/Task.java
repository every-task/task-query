package com.playdata.taskquery.domain.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    private UUID id;
    private Long StoryId;
    @Enumerated(EnumType.STRING)
    private Period period;
    private String content;

    public static Task fromId(UUID id){
        Task task = new Task();
        task.id = id;
        return task;
    }

    public static Task createTask(UUID id, Long storyId, Period period, String content){
        return Task.builder()
                .id(id)
                .storyId(storyId)
                .period(period)
                .content(content)
                .build();
    }

    @Builder
    public Task(UUID id, Long storyId, Period period, String content) {
        this.id = id;
        StoryId = storyId;
        this.period = period;
        this.content = content;
    }
}
