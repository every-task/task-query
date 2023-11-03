package com.playdata.taskquery.domain.suggest.repository;

import com.playdata.taskquery.domain.suggest.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {

    @Query("select s from Suggest s " +
            "left join fetch s.task " +
            "where s.questionId = :questionId")
    List<Suggest> findByQuestionIdFetchTask(Long questionId);
}
