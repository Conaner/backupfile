package org.example.views;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.example.core.base.ZipFileWithPwd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class InputCodeFrame extends JFrame {
    String password;
    String selectDirText1;
    String selectDirText2;
    String extension;
    String final_filename;
    String source_dir;
    String destination_dir;
    boolean select_flag;
    public InputCodeFrame(String selectDirText1, String selectDirText2, String extension, String final_filename, String source_dir, String destination_dir, boolean select_flag){
        this.selectDirText1 = selectDirText1;
        this.selectDirText2 = selectDirText2;
        this.extension = extension;
        this.final_filename = final_filename;
        this.source_dir = source_dir;
        this.destination_dir = destination_dir;
        this.select_flag = select_flag;
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
                password = inputText.getText();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (select_flag) {
                            if (!Objects.equals(selectDirText1, "")) {
                                System.out.println("压缩中...");
                                try {
                                    //                                System.out.println(password);
                                    ZipFileWithPwd.zipFile_dir(selectDirText1, final_filename, password);
                                } catch (ZipException ex) {
                                    throw new RuntimeException(ex);
                                }
                                System.out.println("压缩成功");
                            } else if (!Objects.equals(selectDirText2, "")) {
                                System.out.println("压缩中...");
                                String extension1 = extension;
                                File file = new File(selectDirText2);
                                File[] listFiles = file.listFiles((d, s) -> s.toLowerCase().endsWith(extension1));
                                try {
                                    ZipFileWithPwd.zipFiles(listFiles, final_filename, password);
                                } catch (ZipException ex) {
                                    throw new RuntimeException(ex);
                                }
                                System.out.println("压缩成功");
                            }
                            else{
                                System.out.println("请输入待压缩文件路径");
                            }
                        }
                        else{
                            System.out.println("解压中...");
                            boolean flag = true;
                            try (ZipFile zipFile = new ZipFile(source_dir)) {
                                zipFile.setPassword(password.toCharArray());
                                try {
                                    zipFile.extractAll(destination_dir);
                                } catch (ZipException ex) {
                                    flag = false;
                                    System.out.println("密码错误,解压失败");
                                }
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if(flag) System.out.println("解压成功");
                        }
                    }
                }).start();
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
