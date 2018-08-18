package zz.server.persistence;

import zz.common.model.TodoList;

public interface TodoListPersistence {
    void save(TodoList todoList);

    TodoList read();

}
