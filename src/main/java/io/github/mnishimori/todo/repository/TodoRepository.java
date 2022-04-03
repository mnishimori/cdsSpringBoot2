package io.github.mnishimori.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mnishimori.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
