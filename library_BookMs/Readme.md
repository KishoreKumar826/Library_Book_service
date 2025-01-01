# Library Book Management System

This is a Spring Boot application for managing a library's book inventory. The application provides RESTful APIs for adding, fetching, updating, and deleting books. It also integrates with Kafka for messaging and Eureka for service discovery.

## Features

- Add a new book
- Fetch all books
- Find a book by ISBN
- Delete a book by ISBN
- Kafka messaging for book updates
- Eureka service discovery

## Project Structure
 
```
library_BookMs/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── library/
│   │   │           └── bookms/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── LibraryBookMsApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── library/
│                   └── bookms/
│                       └── LibraryBookMsApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```


## Getting Started

### Prerequisites

- Java 17
- Maven 3.9.9
- Kafka
- Eureka Server

### Running the Application

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/library_BookMs.git
   cd library_BookMs
   

API Endpoints
GET /: Welcome message
GET /fetchAllBooks: Fetch all books
GET /findByISBN/{isbn}: Find a book by ISBN
POST /addNewBook: Add a new book
DELETE /deleteBookByISBN/{isbn}: Delete a book by ISBN
GET /sentData/{message}: Send a message to Kafka
Frontend
The frontend is a simple HTML page located at Book.html. It provides a basic interface for interacting with the API.

use http://localhost:8073/libraryBookMs/Book.html to run