package com.playdata.taskquery.kafka.consumer;

import com.playdata.taskquery.kafka.config.TopicConfig;
import com.playdata.taskquery.kafka.data.SuggestKafkaData;
import com.playdata.taskquery.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestConsumer {

    private final TaskService taskService;

    @KafkaListener(topics = TopicConfig.SUGGEST)
    public void listen(SuggestKafkaData data){
        taskService.suggestRegister(data);
    }
}
