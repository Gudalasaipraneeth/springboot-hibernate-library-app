package com.springproject.SpringProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.SpringProject.models.Genre;

@Repository
public class genreDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Genre addGenre(String name) {
		Genre genre = new Genre();
		genre.setName(name);
		this.sessionFactory.getCurrentSession().saveOrUpdate(genre);
		return genre;
	}

	@Transactional
	public List<Genre> getGenres() {
		return this.sessionFactory.getCurrentSession().createQuery("from GENRE").list();
	}

	@Transactional
	public Boolean deleteGenre(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		Object persistanceInstance = session.load(Genre.class, id);

		if (persistanceInstance != null) {
			session.delete(persistanceInstance);
			return true;
		}
		return false;
	}

	@Transactional
	public Genre updateGenre(int id, String name) {
		Genre genre = this.sessionFactory.getCurrentSession().get(Genre.class, id);
		genre.setName(name);

		this.sessionFactory.getCurrentSession().update(genre);
		return genre;
	}

	@Transactional
	public Genre getGenre(int id) {
		return this.sessionFactory.getCurrentSession().get(Genre.class,id);
	}
}
