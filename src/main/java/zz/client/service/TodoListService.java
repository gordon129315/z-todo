package zz.client.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import zz.client.net.RequestSender;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.common.net.Request;
import zz.common.net.Response;

//Entity -> JSON string
//build request
public class TodoListService {

    public void add(TodoItem item) {
        Request request = new Request();
        request.setAction("add task");

        String data = new Gson().toJson(item);
        request.setData(data);

        new RequestSender().send(request);

    }

    public TodoList get() {
        Request request = new Request();
        request.setAction("get");

        Response response = new RequestSender().send(request);

        String data = response.getData();
        TodoList todoList = new Gson().fromJson(data, TodoList.class);

        return todoList;
    }

    public void delete(int index) {
        Request request = new Request();
        request.setAction("delete");

        String data = new Gson().toJson(index);
        request.setData(data);

        new RequestSender().send(request);

    }

    public void edit(int index, String newString) {
        Request request = new Request();
        request.setAction("edit");

//        String data = new Gson().toJson(index + " " + newString);
//        request.setData(data);

        JsonObject object = new JsonObject();
        object.addProperty("index", index);
        object.addProperty("newString", newString);

        String data = new Gson().toJson(object);
        request.setData(data);

        new RequestSender().send(request);
    }
}
