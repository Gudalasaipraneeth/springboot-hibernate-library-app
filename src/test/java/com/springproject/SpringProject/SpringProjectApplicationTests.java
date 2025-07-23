package com.springproject.SpringProject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.springproject.SpringProject.models.Genre;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.repository.GenreRepository;
import com.springproject.SpringProject.repository.BookRepository;

@SpringBootTest
@ActiveProfiles("test")
class SpringProjectApplicationTests {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> {
            // Context load test
        });
    }

    @Test
    void testGenreRepository() {
        Genre genre = new Genre();
        genre.setName("Test Genre");
        
        Genre savedGenre = genreRepository.save(genre);
        assertNotNull(savedGenre.getId());
        assertEquals("Test Genre", savedGenre.getName());

        Genre foundGenre = genreRepository.findByName("Test Genre");
        assertNotNull(foundGenre);
        assertEquals(savedGenre.getId(), foundGenre.getId());
    }

    @Test
    void testBookRepository() {
        Genre genre = new Genre();
        genre.setName("Book Test Genre");
        Genre savedGenre = genreRepository.save(genre);

        Book book = new Book();
        book.setName("Test Book");
        book.setGenre(savedGenre);
        book.setPrice(100);
        book.setQuantity(10);
        
        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook.getId());
        assertEquals("Test Book", savedBook.getName());
        assertEquals(savedGenre.getId(), savedBook.getGenre().getId());
    }
}
