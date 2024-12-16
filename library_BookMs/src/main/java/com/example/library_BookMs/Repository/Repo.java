package com.example.library_BookMs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_BookMs.Domain.book;

@Repository
public interface Repo extends JpaRepository<book , Integer> {

}
