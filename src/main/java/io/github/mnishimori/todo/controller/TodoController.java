package io.github.mnishimori.todo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.mnishimori.todo.model.Todo;
import io.github.mnishimori.todo.repository.TodoRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	TodoRepository repository;
	
	@PostMapping
	public Todo salvar(@RequestBody Todo todo) {
		return repository.save(todo);
	}
	
	@GetMapping
	public List<Todo> getAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Todo getById(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PatchMapping("/{id}/done")
	public Todo markAsDone(@PathVariable Long id) {
		Todo todo = repository.findById(id)
				.map(t -> {
					t.setDone(true);
					t.setDoneDate(LocalDateTime.now());
					repository.save(t);
					return t;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return todo;
	}

}
