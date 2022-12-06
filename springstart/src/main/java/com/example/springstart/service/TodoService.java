package com.example.springstart.service;


import com.example.springstart.entity.TodoEntity;
import com.example.springstart.entity.UserEntity;
import com.example.springstart.model.Todo;
import com.example.springstart.repozitory.TodoRepo;
import com.example.springstart.repozitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long userId){
        UserEntity user=userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }
    public Todo completeTodo(Long id){
        TodoEntity todo=todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
