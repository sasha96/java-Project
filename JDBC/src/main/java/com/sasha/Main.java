package com.sasha;

import java.sql.*;

public class Main {
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USER = "root";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String sq = "UPDATE users SET id = 2,name = 'pasha',salary = 1500,dt = '1994-04-04' WHERE id = 2";
            statement.executeUpdate(sq);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("salary"));
                System.out.println(resultSet.getDate("dt"));
            }
            DatabaseMetaData metaData = connection.getMetaData();
            String dbProduct = metaData.getDatabaseProductName();
            System.out.println(dbProduct);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}