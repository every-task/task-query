package com.playdata.taskquery.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    public static final String STORY = "story";
    public static final String SUGGEST = "suggest";
    public static final String TASK = "task";

    // local 개발용 토픽, 운영환경은 task service 에서 토픽 생성 X
    @Bean
    public NewTopic topicStory(){
        return TopicBuilder
                .name(STORY)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicSuggest(){
        return TopicBuilder
                .name(SUGGEST)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
