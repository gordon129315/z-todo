package zz.server.persistence;

import org.apache.commons.io.FileUtils;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Scanner;

public class TodoListPersistenceLine implements TodoListPersistence {
    @Override
    public void save(TodoList todoList) {
        StringBuilder builder = new StringBuilder();
        builder.append(todoList.getTitle());
        builder.append("\n");

        LinkedList<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            builder.append(item.getText());
            builder.append("\n");
        }

        String data = builder.toString();

        new File("data").mkdir();

        try {
            File file = new File("data/todo.txt");
            FileUtils.writeStringToFile(file, data, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public TodoList read() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File("data/todo.txt");
        String data = null;
        try {
            data = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Scanner scanner = new Scanner(data);

        TodoList todoList = new TodoList();
        String title = scanner.nextLine();
        todoList.setTitle(title);

        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            TodoItem todoItem = new TodoItem(text);
            todoList.getItems().add(todoItem);
        }

        return todoList;

    }



}
