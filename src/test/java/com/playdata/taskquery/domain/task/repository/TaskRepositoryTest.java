package com.playdata.taskquery.domain.task.repository;

import com.playdata.taskquery.domain.task.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @DisplayName("story id 로 task 를 조회한다.")
    @Test
    void findByStoryId(){
        //given
        Task task1 = Task.builder().id(UUID.randomUUID()).storyId(1L).build();
        Task task2 = Task.builder().id(UUID.randomUUID()).storyId(1L).build();
        Task task3 = Task.builder().id(UUID.randomUUID()).storyId(2L).build();

        taskRepository.saveAll(List.of(task1, task2, task3));

        //when
        List<Task> tasks = taskRepository.findByStoryId(1L);

        //then
        assertThat(tasks).hasSize(2)
                .extracting("storyId")
                .contains(1L);
    }
}