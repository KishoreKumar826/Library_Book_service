package com.example.library_BookMs.Domain;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="book")
public class book {
@NotNull(message="id should not be null")
@Id
private Integer id;
@NotNull(message="title should not be null")
private String title;
@NotNull(message="Date should not be null")
private String published_Date;
@Min(value=1,message="Total copies should be atleast 1")
private Integer total_Copies;
private Integer issued_Copies;
private String author;



public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getPublished_Date() {
    return published_Date;
}
public void setPublished_Date(String published_Date) {
    this.published_Date = published_Date;
}
public Integer getTotal_Copies() {
    return total_Copies;
}
public void setTotal_Copies(Integer total_Copies) {
    this.total_Copies = total_Copies;
}
public Integer getIssued_Copies() {
    return issued_Copies;
}
public void setIssued_Copies(Integer issued_Copies) {
    this.issued_Copies = issued_Copies;
}
public String getAuthor() {
    return author;
}
public void setAuthor(String author) {
    this.author = author;
}




}
