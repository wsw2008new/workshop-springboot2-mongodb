package br.com.vitarts.workshopmongo.services;

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
	
	/*
	public Post fromDTO(PostDTO objDto) {
		return new Post(objDto.getId(), objDto.getDate(), objDto.getTitle(), objDto.getBody, objDto.getAuthorDto());
	}
	*/
	
	public List<Post> findAll() {
		return repo.findAll();
	}

	public Post insert(Post obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = repo.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setDate(obj.getDate());
		newObj.setBody(obj.getBody());
		newObj.setAuthor(obj.getAuthor());
	}
}
