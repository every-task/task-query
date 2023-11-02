package com.playdata.taskquery.domain.suggest.repository;

import com.playdata.taskquery.domain.suggest.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepository extends JpaRepository<Suggest, Long> {
}
