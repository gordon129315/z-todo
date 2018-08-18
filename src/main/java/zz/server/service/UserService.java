package zz.server.service;

import zz.common.model.User;
import zz.server.persistence.UserPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserService {

    private UserPersistence userPersistence = new UserPersistence();

    public boolean register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User existUser = userPersistence.getByUsername(user);
        if (existUser != null) {
            return false;
        }

        user.setPassword(password);
        user = userPersistence.create(user);
        if (user == null) {
            return false;
        }

        return true;
    }

    //Map : token <->userId
    private static Map<String, Long> tokens = new HashMap<>();

    public String login(String username, String password) {
        User user = new User();
        user.setUsername(username);

        User existUser = userPersistence.getByUsername(user);
        if (existUser == null) {
            return null;
        }

        if (!existUser.getPassword().equals(password)) {
            return null;
        }

        //随机生成20位token
        String token = "";
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            token += random.nextInt(10);
        }

        tokens.put(token, existUser.getId());

        return token;
    }

    public Long checkAuthentication(String token) {
        return tokens.get(token);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        boolean register = userService.register("zz4", "pwd");
        System.out.println(register);
    }
}
