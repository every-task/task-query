package com.playdata.taskquery.kafka.consumer;

import com.playdata.taskquery.kafka.config.TopicConfig;
import com.playdata.taskquery.kafka.data.TaskKafkaData;
import com.playdata.taskquery.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskConsumer {

    private final TaskService taskService;

    @KafkaListener(topics = TopicConfig.TASK)
    public void listen(TaskKafkaData data){
        taskService.editTask(data);
    }
}
