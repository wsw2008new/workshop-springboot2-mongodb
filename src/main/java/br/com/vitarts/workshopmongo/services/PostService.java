package br.com.vitarts.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitarts.workshopmongo.domain.Post;
import br.com.vitarts.workshopmongo.repository.PostRepository;
import br.com.vitarts.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}
	
	public List<Post> findbyTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		// by he reason that our database were set to save dates with 00:00:00 time of the day.
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
