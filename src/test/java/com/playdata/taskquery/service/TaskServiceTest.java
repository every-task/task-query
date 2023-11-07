package com.playdata.taskquery.service;

import com.playdata.taskquery.domain.task.entity.Period;
import com.playdata.taskquery.domain.task.entity.Task;
import com.playdata.taskquery.domain.task.repository.TaskRepository;
import com.playdata.taskquery.kafka.data.TaskKafkaData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @DisplayName("task를 수정한다.")
    @Test
    void editTask(){
        //given
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .storyId(1L)
                .period(Period.DAILY)
                .content("물을 많이 마셔요.")
                .build();

        taskRepository.save(task);

        TaskKafkaData data = new TaskKafkaData(task.getId(), Period.ALWAYS, "숨을 쉬세요");

        //when
        taskService.editTask(data);

        //then
        Task editedTask = taskRepository.findById(task.getId()).get();
        assertThat(editedTask.getStoryId()).isEqualTo(1L);
        assertThat(editedTask.getPeriod()).isEqualTo(Period.ALWAYS);
        assertThat(editedTask.getContent()).isEqualTo("숨을 쉬세요");
    }

    @DisplayName("task 수정 시, task가 존재하지 않는다면 새로 생성해 준다.")
    @Test
    void editNotExistTask(){
        //given
        UUID notExistTaskId = UUID.randomUUID();
        TaskKafkaData data = new TaskKafkaData(notExistTaskId, Period.ALWAYS, "숨을 쉬세요");

        //when
        taskService.editTask(data);

        //then
        Task editedTask = taskRepository.findById(notExistTaskId).get();
        assertThat(editedTask.getStoryId()).isNull();
        assertThat(editedTask.getPeriod()).isEqualTo(Period.ALWAYS);
        assertThat(editedTask.getContent()).isEqualTo("숨을 쉬세요");
    }

}