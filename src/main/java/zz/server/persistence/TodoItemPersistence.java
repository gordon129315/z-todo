package zz.server.persistence;

import zz.common.model.TodoItem;
import zz.common.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TodoItemPersistence {

    public static TodoItem fromResult(ResultSet resultSet) {
        try {
            TodoItem todoItem = new TodoItem();
            todoItem.setId(resultSet.getLong("id"));
            todoItem.setUserId(resultSet.getLong("user_id"));
            todoItem.setText(resultSet.getString("text"));
            return todoItem;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TodoItem> getByUser(User user) {
        Connection connection = Database.newConnection();
        String query = "SELECT * FROM todo_item WHERE user_id = %d;";
        query = String.format(query, user.getId());

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            LinkedList<TodoItem> todoItems = new LinkedList<>();
            while (resultSet.next()) {
                TodoItem todoItem = TodoItemPersistence.fromResult(resultSet);
                todoItems.add(todoItem);
            }
            return todoItems;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        TodoItemPersistence todoItemPersistence = new TodoItemPersistence();
        User user = new User();
        user.setId(18);
        List<TodoItem> todoItems = todoItemPersistence.getByUser(user);
        System.out.println(todoItems);
    }


}