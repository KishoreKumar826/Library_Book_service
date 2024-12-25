package com.example.library_BookMs.KafkfaConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.library_BookMs.Controller.BookController;
import com.example.library_BookMs.Domain.book;
import com.example.library_BookMs.Repository.Repo;

@Service
@EnableKafka
public class Consumer {
    @Autowired
    private Repo repo;
    @Autowired
    private KafkaTemplate<String,String> kafka;

    @KafkaListener(topics = "produceisbn", groupId = "b")
    public void consumer(String message) {
        System.out.println("Isbn  received " + message);
        Integer num = Integer.parseInt(message);
        book bok = repo.findById(num).get();
        int available_copies = bok.getTotal_Copies() - bok.getIssued_Copies();
        System.out.println("Available copies of " + num + " is " + available_copies);
        String numstr=String.valueOf(available_copies);
        kafka.send("produceAvailableSize", "Availablesize", numstr);
    }

    @KafkaListener(topics = "issuedCopies", groupId = "b")
    public void updatesIssueCopies(ConsumerRecord<String, String> record) {
        String isbn=record.key();
        String issuedCopy=record.value();
        Integer isbn_num = Integer.parseInt(isbn);
        Integer issuedCopy_num = Integer.parseInt(issuedCopy);
        book bok = repo.findById(isbn_num).get();
        bok.setIssued_Copies(bok.getIssued_Copies()+issuedCopy_num);
        repo.save(bok);
        System.out.println("Issued Copy update");
    }

}
