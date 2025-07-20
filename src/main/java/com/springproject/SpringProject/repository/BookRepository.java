package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Add custom queries if needed
}
