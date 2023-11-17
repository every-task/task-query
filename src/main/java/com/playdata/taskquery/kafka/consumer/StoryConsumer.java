package com.playdata.taskquery.kafka.consumer;

import com.playdata.taskquery.kafka.config.KafkaAction;
import com.playdata.taskquery.kafka.config.KafkaData;
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

    // TODO : 더 좋은 action 구분 방법..
    // @Controller, @GepMapping 처럼
    // 만들어봐도 재밌을듯
    @KafkaListener(topics = TopicConfig.STORY)
    public void listen(KafkaData<StoryKafkaData> kafkaData){
        if(kafkaData.action() == KafkaAction.CREATE){
            handleCreate(kafkaData.data());
        }
    }

    public void handleCreate(StoryKafkaData data){
        taskService.taskRegister(data);
    }
}
