package com.springproject.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.repository.BookRepository;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.models.Genre;

@Service
public class bookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public Book getBook(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public Book updateBook(int id, Book book) {
		book.setId(id);
		return bookRepository.save(book);
	}

	public boolean deleteBook(int id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
