package org.example.views;

import org.example.core.base.BaseFrame;
import org.example.core.base.CheckUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends BaseFrame {
    public static void initAndShow() {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    public void closeThis(){
        this.dispose();
    }
    public MainFrame() throws HeadlessException {
        super("登录界面");
    }

    @Override
    public void initView() {
        super.initView();

        Container pane = this.getContentPane();
        pane.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(110,70,80,25);
        pane.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(170,70,165,25);
        pane.add(userText);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(110,100,80,25);
        pane.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(170,100,165,25);
        pane.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(275, 140, 70, 25);
        pane.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckUser checkuser= new CheckUser();
                checkuser.setUser(userText.getText());
                checkuser.setPassword(passwordText.getText());

//                System.out.println(userText.getText());
//                System.out.println(passwordText.getPassword());

                boolean judge = checkuser.CheckRole();
                if(judge){
                    System.out.println("登录成功！");
                    closeThis();
                    SecondFrame ScdFr = new SecondFrame(userText.getText());
                    SecondFrame.initAndShow();
                }
                else System.out.println("用户不存在！");
//                System.out.println("judge = "+ judge);
//                System.out.println(userText.getText());
            }
        });

        JButton AdminButton = new JButton("注册");
        AdminButton.setBounds(160, 140, 70, 25);
        pane.add(AdminButton);
        AdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
            }
        });
    }

}
