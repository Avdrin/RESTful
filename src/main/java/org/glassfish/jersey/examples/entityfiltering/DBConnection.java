package org.glassfish.jersey.examples.entityfiltering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

    Connection connection = null;
    String url;
    String login;
    String password;



    protected boolean loadDriver() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("Драйвер известен");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected Connection getConnection() {
        login = "root";
        password = "";

        try {
            String path = "mypath/";
            String dbname = "mydb";
            String connectionString = "jdbc:hsqldb:file:"+path+dbname;
            connection = DriverManager.getConnection(connectionString, login, password);
            System.out.println("Соединение установлено");

        } catch (SQLException e) {
            System.out.println("Соединение не создано");
            e.printStackTrace();
            return null;
        }
        return connection;
    }


    protected void closeConnection() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SHUTDOWN";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
