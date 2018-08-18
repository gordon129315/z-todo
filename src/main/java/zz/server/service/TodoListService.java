package zz.server.service;

import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.server.persistence.TodoListPersistence;
import zz.server.persistence.TodoListPersistenceJson;

public class TodoListService {

    // in memory model

//    private TodoList todoList;
    private TodoListPersistence persistence = new TodoListPersistenceJson();

    // services

    public void add(TodoItem item) {
        TodoList todoList = get();
        todoList.getItems().add(item);
        persistence.save(todoList);
    }

    public TodoList get() {
//        if (todoList == null) {
//            todoList = new TodoList();
//            todoList.setTitle("Todo");
//            todoList.getItems().register(new TodoItem("Git"));
//            todoList.getItems().register(new TodoItem("swift"));
//            todoList.getItems().register(new TodoItem("react"));
//        }

        TodoList todoList = persistence.read();
        if (todoList == null) {
            todoList = new TodoList();
            todoList.setTitle("Todo");
            persistence.save(todoList);
        }
        return todoList;
    }

//    public void delete(TodoItem item) {
//        TodoList todoList = get();
//        todoList.getItems().remove(item);
//        persistence.save(todoList);
//    }
    public void delete(int index) {
        TodoList todoList = get();
        todoList.getItems().remove(index);
        persistence.save(todoList);
    }

    public void edit(int index, String newString) {
        TodoList todoList = get();
        todoList.getItems().get(index).setText(newString);
        persistence.save(todoList);
    }
}
