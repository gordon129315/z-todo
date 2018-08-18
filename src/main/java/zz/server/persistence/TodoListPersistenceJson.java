package zz.server.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import zz.common.model.TodoList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class TodoListPersistenceJson implements TodoListPersistence {
    @Override
    public void save(TodoList todoList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = gson.toJson(todoList);
//        System.out.println(data);

        try {
            File file = new File("data/todo.json");
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
        File file = new File("data/todo.json");
        String data = null;
        try {
            data = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        TodoList todoList = gson.fromJson(data, TodoList.class);

        return todoList;
    }


}
