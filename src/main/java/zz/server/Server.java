package zz.server;

import com.zzpublic.socket.Connector;
import zz.server.net.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



//Listen
//accept
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("server start up");

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();  //等待client
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }


            final Socket socketInThread = socket;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("socket connected");

                    Connector connector = new Connector(socketInThread);
                    RequestHandler requestHandler = new RequestHandler();
                    requestHandler.handler(connector);
                }
            }).start();


        }

    }
}
