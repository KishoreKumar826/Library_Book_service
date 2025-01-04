
/**
 * BookController is a REST controller that handles HTTP requests for managing books in the library.
 * It provides endpoints to add, delete, fetch, and find books by ISBN.
 * It also includes a Kafka producer to send messages to a Kafka topic.
 * 
 * Endpoints:
 * - GET /: Returns a welcome message.
 * - GET /sentData/{message}: Sends a message to the Kafka topic "BookCopies".
 * - GET /fetchAllBooks: Fetches all books from the repository.
 * - GET /findByISBN/{isbn}: Finds a book by its ISBN.
 * - DELETE /deleteBookByISBN/{isbn}: Deletes a book by its ISBN.
 * - POST /addNewBook: Adds a new book to the repository.
 * 
 * Dependencies:
 * - Repo: Repository interface for accessing book data.
 * - KafkaTemplate: Kafka template for sending messages to Kafka topics.
 * 
 * Annotations:
 * - @RestController: Indicates that this class is a REST controller.
 * - @Autowired: Injects the required dependencies.
 * - @GetMapping: Maps HTTP GET requests to handler methods.
 * - @DeleteMapping: Maps HTTP DELETE requests to handler methods.
 * - @PostMapping: Maps HTTP POST requests to handler methods.
 * - @RequestBody: Binds the HTTP request body to a method parameter.
 * - @PathVariable: Binds a URI template variable to a method parameter.
 * - @ResponseBody: Indicates that the return value of a method should be used as the response body.
 */// The @Valid annotation is used to mark a method parameter for validation.
// It ensures that the object passed as a parameter meets the validation constraints defined in its class.
// If the validation fails, a MethodArgumentNotValidException is thrown, which can be handled to provide appropriate error responses.

package com.example.library_BookMs.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.library_BookMs.Domain.book;
import com.example.library_BookMs.Repository.Repo;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class BookController {
    @Autowired
    private Repo repo;
    @Autowired
    private KafkaTemplate<String,String> kafka ;

    @GetMapping("/")
    public String getMethodName() {
        return "Welcome to Book service";
    }

    @GetMapping("/sentData/{message}")
    public String publish(@PathVariable String message){
        kafka.send("BookCopies", message, message);
        System.out.println(" message sent "+message);
        return "Message sent!";
    }

   

    @GetMapping("/fetchAllBooks")
    public List<book> fetchAllBooks(){
        return repo.findAll();

        
    }
    
    
    @GetMapping("/findByISBN/{isbn}")
    public ResponseEntity<book> findByISBN(@Valid @PathVariable Integer isbn){
         book found=repo.findById(isbn).get();
         if(found==null){
          return  ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok().body(found);

    }

    @DeleteMapping("/deleteBookByISBN/{isbn}")
    public ResponseEntity<Object> deleteBookByISBN(@Valid @PathVariable Integer isbn){
       book found=repo.findById(isbn).get();
       if(null==found){
        return ResponseEntity.notFound().build();
       }
       repo.deleteById(isbn);
       return ResponseEntity.ok().build();
    }

    @PostMapping("/addNewBook")
    //client from web browser sent data in json format. @requestbody used to convert it to java object
    @Transactional
    // The @Transactional annotation ensures that the method it is applied to is executed within a transactional context.
// This means that all database operations within the method will be part of a single transaction.
// If any operation fails, the transaction will be rolled back, ensuring data consistency and integrity.
    public ResponseEntity<Object> addNewBook(@Valid @RequestBody book bookobj){
        repo.save(bookobj);
        return ResponseEntity.ok(bookobj);

        
    }
    

}
