package zz.server.net;

import com.zzpublic.socket.Connector;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.controller.TodoItemController;
import zz.server.controller.TodoListController;
import zz.server.controller.UserController;
import zz.server.service.UserService;

//receive request
//rebuild request
//routing
public class RequestHandler {
    public void handler(Connector connector) {
        // receive from RequestSender
        String token = connector.readLine();
        String action = connector.readLine();
        String data = connector.readLine();

//        System.out.println(action);
//        System.out.println(data);

        Request request = new Request();
        request.setAction(action);
        request.setData(data);

        Response response = new Response();

        if (action.equals("add task")) {
            new TodoListController().add(request, response);
        }
        else if (action.equals("get")) {
            new TodoListController().get(request, response);
        }
        else if (action.equals("delete")) {
            new TodoListController().delete(request, response);
        }
        else if (action.equals("edit")) {
            new TodoListController().edit(request, response);
        }
        else if (action.equals("user register")) {
            new UserController().register(request, response);
        }
        else if (action.equals("user login")) {
            new UserController().login(request, response);
        }
        //以下都是需要用到token的敏感操作
        else {
            Long userId = new UserService().checkAuthentication(token);

            if (userId == null) {
                response.setStatus(Response.failure);
            }
            else {
                request.setCurrentUserId(userId);
                if (action.equals("todo get all by one user")) {
                    new TodoItemController().getAll(request, response);
                }
            }

        }

        // send back to RequestSender
        connector.writeLine(response.getStatus());
        connector.writeLine(response.getData());

    }
}
