package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    public TodoEntity add(TodoRequest requset) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(requset.getTitle());
        todoEntity.setOrder(requset.getOrder());
        todoEntity.setCompleted((requset.getCompleted()));
        TodoEntity entity = this.todoRepository.save(todoEntity);

        return entity;
    }

    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND )));
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);

        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }

        if (request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }

        if (request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }

        return this.todoRepository.save(todoEntity);
    }

    public void deleteById(Long id) {

        this.todoRepository.deleteById(id);

    }

    public void deleteAll() {

        this.todoRepository.deleteAll();

    }
}
