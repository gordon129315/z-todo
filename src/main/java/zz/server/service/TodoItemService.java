package zz.server.service;

import zz.common.model.TodoItem;
import zz.common.model.User;
import zz.server.persistence.TodoItemPersistence;

import java.util.List;

public class TodoItemService {
    private TodoItemPersistence todoItemPersistence = new TodoItemPersistence();

    public List<TodoItem> getByUserId(long userId) {
        return todoItemPersistence.getByUser(new User(userId));
    }

}
