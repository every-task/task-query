package com.playdata.taskquery.domain.suggest.entity;

import com.playdata.taskquery.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Suggest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long QuestionId;
    @ManyToOne
    private Task task;
}
