package org.example.views;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.example.core.base.BaseFrame;
import org.example.core.base.CheckUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class MainFrame extends BaseFrame {
    public static void initAndShow() {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public MainFrame() throws HeadlessException {
        super("title");
    }

    @Override
    public void initView() {
        super.initView();

        // 添加 "Hello World" 标签
//        JLabel label = new JLabel("Hello World");
//        this.getContentPane().add(label);

        Container pane = this.getContentPane();
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        pane.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        pane.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        pane.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        pane.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        pane.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        pane.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckUser checkuser= new CheckUser();
                checkuser.setUser(String.valueOf(userText));
                checkuser.setPassword(String.valueOf(passwordText));

                System.out.println(userText.getText());
                System.out.println(passwordText.getPassword());

                boolean judge = checkuser.CheckRole();
                System.out.println(judge);
//                System.out.println(userText.getText());
            }
        });


        // 输入密码的文本域
        JLabel selectDir = new JLabel("选择路径:");
        selectDir.setBounds(10,120,80,25);
        pane.add(selectDir);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JTextField selectDirText = new JTextField(20);
        selectDirText.setBounds(100,120,165,25);
        pane.add(selectDirText);

        selectDirText.setText("E:\\测试目录");

        JButton selectFileButton = new JButton("选择");
        selectFileButton.setBounds(100 + 170 + 10 , 120, 80, 25);
        pane.add(selectFileButton);

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
                jfc.showDialog(new JLabel(), "选择文件夹");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                    selectDirText.setText(file.getAbsolutePath());
                }else if(file.isFile()){
                    System.out.println("请选择文件夹");
                }
            }
        });

        String backupFilepathDir = "E:\\DataBackup";

        JButton zipButton = new JButton("压缩");
        zipButton.setBounds(10 , 150, 80, 25);
        pane.add(zipButton);
        zipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file = backupFilepathDir + "\\" + System.currentTimeMillis() + ".zip";

                ZipParameters zipParameters = new ZipParameters();
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);



                ZipFile zipFile = new ZipFile(file, "password123".toCharArray());
                File fileDir = new File(selectDirText.getText());
                try {
                    zipFile.addFolder(fileDir,zipParameters);
                    System.out.println("压缩成功");
                } catch (ZipException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
