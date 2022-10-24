package org.example.core.base;

import javax.swing.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String title) {
        super(title);
        initView();
    }

    public void initView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示窗口

        this.setSize(500,300) ;
        this.setLocationRelativeTo(null);
    }
}
