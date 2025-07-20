package com.springproject.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.repository.GenreRepository;
import com.springproject.SpringProject.models.Genre;

import net.bytebuddy.dynamic.DynamicType.Builder.InnerTypeDefinition;

@Service
public class genreService {
	@Autowired
	private GenreRepository genreRepository;

	public Genre addGenre(String name) {
		Genre genre = new Genre();
		genre.setName(name);
		return genreRepository.save(genre);
	}

	public List<Genre> getGenres() {
		return genreRepository.findAll();
	}

	public Boolean deleteGenre(int id) {
		if (genreRepository.existsById(id)) {
			genreRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Genre updateGenre(int id, String name) {
		return genreRepository.findById(id).map(genre -> {
			genre.setName(name);
			return genreRepository.save(genre);
		}).orElse(null);
	}

	public Genre getGenre(int id) {
		return genreRepository.findById(id).orElse(null);
	}
}
