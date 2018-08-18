package zz.server.persistence;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {
    public static Connection newConnection() {
        Properties properties = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream("server.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String host = properties.getProperty("database.host");
        String databaseName = properties.getProperty("database.name");
        String url = "jdbc:postgresql://" + host + "/" + databaseName;
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = newConnection();
        String query = "SELECT * FROM \"user\";";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
