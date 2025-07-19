package com.springproject.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.dao.genreDao;
import com.springproject.SpringProject.models.Genre;

import net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition;

@Service
public class genreService {
	@Autowired
	private genreDao genreDao;
	
	public Genre addGenre(String name) {
		return this.genreDao.addGenre(name);
	}
	
	public List<Genre> getGenres(){
		return this.genreDao.getGenres();
	}
	
	public Boolean deleteGenre(int id) {
		return this.genreDao.deleteGenre(id);
	}
	
	public Genre updateGenre(int id,String name) {
		return this.genreDao.updateGenre(id, name);
	}

	public Genre getGenre(int id) {
		return this.genreDao.getGenre(id);
	}
}
