package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Find books by genre
    List<Book> findByGenre(Genre genre);
    
    // Find books by name containing (case insensitive search)
    List<Book> findByNameContainingIgnoreCase(String name);
    
    // Find books by price range
    List<Book> findByPriceBetween(int minPrice, int maxPrice);
    
    // Find books with quantity greater than 0
    List<Book> findByQuantityGreaterThan(int quantity);
}
