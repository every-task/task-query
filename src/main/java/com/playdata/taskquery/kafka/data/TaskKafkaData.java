package com.playdata.taskquery.kafka.data;

import com.playdata.taskquery.domain.task.entity.Period;

import java.util.UUID;

public record TaskKafkaData(
        UUID id,
        Period period,
        String content
) {
}
