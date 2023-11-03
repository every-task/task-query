package com.playdata.taskquery.domain.suggest.repository;

import com.playdata.taskquery.domain.suggest.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {

    List<Suggest> findByQuestionId(Long id);
}
