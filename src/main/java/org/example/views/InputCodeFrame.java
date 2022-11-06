package org.example.views;

import net.lingala.zip4j.exception.ZipException;
import org.example.core.base.BaseFrame;
import org.example.core.base.ZipFileWithPwd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class InputCodeFrame extends JFrame {
    String password;
    JTextField selectDirText1;
    JTextField selectDirText2;
    JTextField extension;
    String final_filename;
    public InputCodeFrame(JTextField selectDirText1,JTextField selectDirText2,JTextField extension,String final_filename){
        this.selectDirText1 = selectDirText1;
        this.selectDirText2 = selectDirText2;
        this.extension = extension;
        this.final_filename = final_filename;
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
                System.out.println("压缩中...");
                password = inputText.getText();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!Objects.equals(selectDirText1.getText(), "")) {
                            try {
//                                System.out.println(password);
                                ZipFileWithPwd.zipFile_dir(selectDirText1.getText(), final_filename, password);
                            } catch (ZipException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (!Objects.equals(selectDirText2.getText(), "")) {
                            String extension1 = extension.getText();
                            File file = new File(selectDirText2.getText());
                            File[] listFiles = file.listFiles((d, s) -> s.toLowerCase().endsWith(extension1));
                            try {
                                ZipFileWithPwd.zipFiles(listFiles, final_filename, password);
                            } catch (ZipException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }).start();

                System.out.println("压缩成功");
                frame.dispose();
//                return inputText.getText();
            }
        });

    }
//    public String getPassword(){return password;}
//    public static void main(String[] args) {
//        InputCodeFrame frame = new InputCodeFrame();
//    }
}
