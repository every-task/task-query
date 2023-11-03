package com.playdata.taskquery.kafka.data;

import java.util.List;
import java.util.UUID;

public record SuggestKafkaData(Long questionId, List<UUID> taskIds) {
}
