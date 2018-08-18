package zz.client.service;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import zz.client.net.RequestSender;
import zz.common.model.TodoItem;
import zz.common.net.Request;
import zz.common.net.Response;

import java.util.LinkedList;
import java.util.List;

public class TodoItemService {
    public List<TodoItem> get() {
        Request request = new Request();
        request.setAction("todo get all by one user");

        Response response = new RequestSender().send(request);
        String data = response.getData();
        Gson gson = new Gson();
        LinkedList<LinkedTreeMap<String, Object>> linkedList = gson.fromJson(data, LinkedList.class);

        LinkedList<TodoItem> todoItems = new LinkedList<>();
        for (LinkedTreeMap<String, Object> treeMap : linkedList) {
            TodoItem todoItem = new TodoItem();
            // miss some codes
            // todoItem some how get item from treeMap
            // todoItems.register(todoItem)
        }
        return todoItems;
    }
}
