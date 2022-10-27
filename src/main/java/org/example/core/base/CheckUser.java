package org.example.core.base;

import java.sql.*;

public class CheckUser{
    //获得提交的用户名和密码
    private String user="";
    private String password="";
    public void setUser(String user){
        this.user=user;
    }
    public void setPassword(String password){
        this.password=password;
    }
    //连接数据库对象初始化
    Connection con=null;
    PreparedStatement stmt=null;
    ResultSet result=null;
    String url="jdbc:mysql://localhost:3306/db1";
    String name="root";
    String pass="root";
    //连接数据库
    public CheckUser(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,name,pass);
//            if(con!=null) System.out.println("connect!");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //验证身份
    public boolean CheckRole(){
//        boolean ifright=false;
        try{
            String sql= "Select * from account where username='" +
                    this.user + "' and password='" + this.password + "'";
//            System.out.println("sql="+sql);
            stmt=con.prepareStatement(sql);
//            stmt.setString(1,this.user);
//            stmt.setString(2,this.password);
            result=stmt.executeQuery(sql);
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result!=null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}