package org.example;

import java.io.IOException;
import java.sql.*;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.用户信息和url
        String url = "jdbc:mysql://localhost:3306/db1";
        String username = "root";
        String password = "root";

        //3.连接数据库connection对象
        Connection connection = DriverManager.getConnection(url,username,password);

        //4.执行sql对象statement
        Statement statement = connection.createStatement();

        //5.去执行sql
        String sql = "SELECT * FROM `account`";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("username=" + resultSet.getObject("username"));
            System.out.println("password=" + resultSet.getObject("password"));

            System.out.println("----------------------------------------");
        }

//        6.释放链接
        resultSet.close();
        statement.close();
        connection.close();

    }
}


