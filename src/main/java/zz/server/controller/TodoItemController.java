package zz.server.controller;

import com.google.gson.Gson;
import zz.common.model.TodoItem;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.TodoItemService;

import java.util.List;

public class TodoItemController {
    private TodoItemService todoItemService = new TodoItemService();

    public void getAll(Request request, Response response) {
        List<TodoItem> items = todoItemService.getByUserId(request.getCurrentUserId());
        Gson gson = new Gson();
        String json = gson.toJson(items);

        response.setStatus(Response.success);
        response.setData(json);
    }
}
