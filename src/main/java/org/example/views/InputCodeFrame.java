package org.example.views;

import org.example.core.base.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputCodeFrame extends JFrame {
    private String password;

    public InputCodeFrame()  {
        initView();
    }
    public void initView() {
        JFrame frame = new JFrame("压缩包密码");

        frame.setLayout(null);

        Container container = frame.getContentPane();

        JLabel guide = new JLabel("请输入密码:");
        guide.setBounds(10, 50-30, 80, 25);
        container.add(guide);

        JTextField inputText = new JTextField(20);
        inputText.setBounds(100, 50-30, 165, 25);
        container.add(inputText);

        JButton Button = new JButton("确定");
        Button.setBounds(280,50-30, 80, 25);
        container.add(Button);

        frame.setBounds(100, 100, 390,100);
//        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
//                return inputText.getText();
            }
        });

    }
    public String getPassword(){return password;}
    public static void main(String[] args) {
        InputCodeFrame frame = new InputCodeFrame();
    }
}
