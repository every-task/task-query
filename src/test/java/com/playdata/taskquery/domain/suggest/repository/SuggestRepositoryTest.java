package com.playdata.taskquery.domain.suggest.repository;

import com.playdata.taskquery.domain.suggest.entity.Suggest;
import com.playdata.taskquery.domain.task.entity.Task;
import com.playdata.taskquery.domain.task.repository.TaskRepository;
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
class SuggestRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SuggestRepository suggestRepository;

    @DisplayName("question id 로 suggest task 를 조회한다.")
    @Test
    void findByStoryId() {
        //given
        Task task1 = Task.builder().id(UUID.randomUUID()).storyId(1L).build();
        Task task2 = Task.builder().id(UUID.randomUUID()).storyId(1L).build();

        taskRepository.saveAll(List.of(task1, task2));

        Suggest suggest1 = Suggest.builder().questionId(1L).task(task1).build();
        Suggest suggest2 = Suggest.builder().questionId(1L).task(task2).build();

        suggestRepository.saveAll(List.of(suggest1, suggest2));

        //when
        List<Suggest> suggests = suggestRepository.findByQuestionIdFetchTask(1L);

        //then
        assertThat(suggests).hasSize(2)
                .extracting("questionId")
                .contains(1L);
    }
}