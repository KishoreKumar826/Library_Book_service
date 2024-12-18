package com.example.library_BookMs.KafkaAdmin;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class createNewTopic {
    //BookCopies": The name of the topic.
//3: The number of partitions the topic should have.
//(short) 1: The replication factor, which indicates how many copies of each partition should be stored across different brokers for fault tolerance.

    @Bean
    public NewTopic createTopic(){
        System.out.println("------------Topic created---------");
        return new NewTopic("BookCopies", 3,(short)1);
    }

}
