package org.example.core.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Register {
    String name;
    String ID;
    String password;
    String confirmpassword;

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://112.74.100.139:3306/db1?serverTimezone=UTC&characterEncoding=utf-8";
    private final String user = "root";
    private final String sqlpassword = "123456";

    public void setName(String name) {
        this.name = name;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmpasswd(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }


    //判断注册的账号是否符合规则
    public boolean JudgeRegister() throws SQLException, ClassNotFoundException {

        if(this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！", "用户名", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.ID.equals("")) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.password.equals("")) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "密码为空", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!this.password.equals(this.confirmpassword)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //符合规则，弹出注册成功的窗口，并将账号添加数据库
        JOptionPane.showMessageDialog(null, "注册成功");
        addAccount();
        return true;
    }

    //向数据库添加Admin账户
    void addAccount() throws ClassNotFoundException, SQLException {
        String sql="insert into account (id, username, password) values (?,?,?)";
        Class.forName(driver);
        try {
            Connection conn = DriverManager.getConnection(url, user, sqlpassword);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.ID);
            ps.setString(2, this.name);
            ps.setString(3, this.password);
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch(SQLException ex) {
            System.out.println("添加用户失败！");
        }

    }
}

