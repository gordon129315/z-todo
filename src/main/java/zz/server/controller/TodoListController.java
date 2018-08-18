package zz.server.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.TodoListService;

import java.util.Arrays;

//JSON -> Entity
//call service
public class TodoListController {

    private TodoListService service = new TodoListService();

    public void add(Request request, Response response) {
        Gson gson = new Gson();
        TodoItem todoItem = gson.fromJson(request.getData(), TodoItem.class);

        service.add(todoItem);

        response.setStatus("success");
    }

    public void get(Request request, Response response) {
        TodoList todoList = service.get();

        response.setStatus("success");
        String data = new Gson().toJson(todoList);
        response.setData(data);
    }

    public void delete(Request request, Response response) {

        int data = Integer.parseInt(request.getData());
        service.delete(data);

        response.setStatus("success");
    }

    public void edit(Request request, Response response) {
//        String data = request.getData();
//        data = data.substring(1, data.length() - 1);
//        String[] args = data.split(" ", 2);
//        service.edit(Integer.parseInt(args[0]), args[1]);

        Gson gson = new Gson();
        JsonObject object = gson.fromJson(request.getData(), JsonObject.class);
        int index = object.get("index").getAsInt();
        String newString = object.get("newString").getAsString();
        service.edit(index, newString);

        response.setStatus("success");
    }
}
