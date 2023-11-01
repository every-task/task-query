package com.playdata.taskquery.kafka.consumer;

import com.playdata.taskquery.kafka.config.TopicConfig;
import com.playdata.taskquery.kafka.data.StoryKafkaData;
import com.playdata.taskquery.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryConsumer {

    private final TaskService taskService;

    @KafkaListener(topics = TopicConfig.STORY)
    public void listen(StoryKafkaData data){
        taskService.register(data);
    }
}
