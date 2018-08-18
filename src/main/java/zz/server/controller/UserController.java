package zz.server.controller;

import com.google.gson.Gson;

import zz.common.model.User;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.UserService;

//JSON -> Entity
//call service
public class UserController {

    private UserService service = new UserService();

    public void register(Request request, Response response) {
        Gson gson = new Gson();
        User user = gson.fromJson(request.getData(), User.class);

        boolean success = service.register(user.getUsername(), user.getPassword());

        String status = success ? Response.success : Response.failure;
        response.setStatus(status);

    }

    public void login(Request request, Response response) {
        Gson gson = new Gson();
        User user = gson.fromJson(request.getData(), User.class);

        String token = service.login(user.getUsername(), user.getPassword());

        if (token == null) {
            response.setStatus(Response.failure);
            return;
        }

        response.setStatus(Response.success);
        response.setData(token);
    }

}
