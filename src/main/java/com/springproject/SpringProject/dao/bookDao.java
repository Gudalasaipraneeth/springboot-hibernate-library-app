package com.springproject.SpringProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.SpringProject.models.Genre;
import com.springproject.SpringProject.models.Book;

@Repository
public class bookDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Transactional
	public List<Book> getBooks(){
		return this.sessionFactory.getCurrentSession().createQuery("from BOOK").list();
	}
	
	@Transactional
	public Book addBook(Book book) {
		this.sessionFactory.getCurrentSession().save(book);
		return book;
	}
	
	@Transactional
	public Book getBook(int id) {
		return this.sessionFactory.getCurrentSession().get(Book.class, id);
	}

	public Book updateBook(Book book){
		this.sessionFactory.getCurrentSession().update(String.valueOf(Book.class),book);
		return book;
	}
	@Transactional
	public Boolean deleteBook(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Object persistanceInstance = session.load(Book.class, id);

		if (persistanceInstance != null) {
			session.delete(persistanceInstance);
			return true;
		}
		return false;
	}

}
