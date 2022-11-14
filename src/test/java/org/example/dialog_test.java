package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//主窗口
public class dialog_test extends JFrame {
    public dialog_test(){
        this.setVisible(true);
        this.setSize(700,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //JFrame放东西需要容器
        Container container = this.getContentPane();
        //绝对布局
        container.setLayout(null);

        //按钮
        JButton button = new JButton("点击弹出一个对话框");//创建对象
        button.setBounds(30,30,200,50);

        //点击这个按钮的时候弹出一个弹窗
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹窗
                new MyDialogDemo();
            }
        });
        container.add(button);
    }

    public static void main(String[] args) {
        new dialog_test();
    }
}
//弹窗的窗口
class MyDialogDemo extends JDialog{
    public MyDialogDemo(){
        this.setVisible(true);
        this.setBounds(100,100,500,500);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);不用添加,弹窗默认有关闭

        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel("你好呀");
        label.setBounds(100,100,200,300);
        container.add(label);

    }
}
