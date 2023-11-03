package com.playdata.taskquery.domain.suggest.entity;

import com.playdata.taskquery.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    @ManyToOne
    private Task task;

    public static Suggest createSuggest(Long questionId, Task task){
        return Suggest.builder()
                .questionId(questionId)
                .task(task)
                .build();
    }

    @Builder
    public Suggest(Long questionId, Task task) {
        this.questionId = questionId;
        this.task = task;
    }
}
