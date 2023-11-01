package com.playdata.taskquery.kafka.data;

import java.util.List;

public record StoryKafkaData(Long id, List<TaskKafkaData> tasks) {
}
