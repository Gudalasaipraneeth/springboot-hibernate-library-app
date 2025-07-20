package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    // Add custom queries if needed
}
