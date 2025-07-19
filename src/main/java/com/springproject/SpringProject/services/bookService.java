package com.springproject.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.dao.bookDao;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.models.Genre;

@Service
public class bookService {
	@Autowired
	private bookDao bookDao;
	
	public List<Book> getBooks(){
		return this.bookDao.getBooks();
	}
	
	public Book addBook(Book book) {
		return this.bookDao.addBook(book);
	}
	
	public Book getBook(int id) {
		return this.bookDao.getBook(id);
	}

	public Book updateBook(int id,Book book){
		book.setId(id);
		return this.bookDao.updateBook(book);
	}
	public boolean deleteBook(int id) {
		return this.bookDao.deleteBook(id);
	}

	
}
