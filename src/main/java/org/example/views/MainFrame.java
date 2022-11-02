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
//        System.out.println("here");
        // 添加 "Hello World" 标签
//        JLabel label = new JLabel("Hello World");
//        this.getContentPane().add(label);

        Container pane = this.getContentPane();
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        pane.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("用户名:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(110,70,80,25);
        pane.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(170,70,165,25);
        pane.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(110,100,80,25);
        pane.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
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

        // 输入密码的文本域
//        JLabel selectDir = new JLabel("选择路径:");
//        selectDir.setBounds(10,120,80,25);
//        pane.add(selectDir);
//
//        /*
//         *这个类似用于输入的文本域
//         * 但是输入的信息会以点号代替，用于包含密码的安全性
//         */
//        JTextField selectDirText = new JTextField(20);
//        selectDirText.setBounds(100,120,165,25);
//        pane.add(selectDirText);
//
//        selectDirText.setText("E:\\测试目录");
//
//        JButton selectFileButton = new JButton("选择");
//        selectFileButton.setBounds(100 + 170 + 10 , 120, 80, 25);
//        pane.add(selectFileButton);
//
//        selectFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser jfc=new JFileChooser();
//                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
//                jfc.showDialog(new JLabel(), "选择文件夹");
//                File file=jfc.getSelectedFile();
//                if(file.isDirectory()){
//                    System.out.println("文件夹:"+file.getAbsolutePath());
//                    selectDirText.setText(file.getAbsolutePath());
//                }else if(file.isFile()){
//                    System.out.println("请选择文件夹");
//                }
//            }
//        });
//
//        String backupFilepathDir = "E:\\DataBackup" + userText.getText();
//
//        JButton zipButton = new JButton("压缩");
//        zipButton.setBounds(10 , 150, 80, 25);
//        pane.add(zipButton);
//        zipButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String file = backupFilepathDir + "\\" + System.currentTimeMillis() + ".zip";
//
//                ZipParameters zipParameters = new ZipParameters();
//                zipParameters.setEncryptFiles(true);
//                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
//                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
//
//
//
//                ZipFile zipFile = new ZipFile(file, "password123".toCharArray());
//                File fileDir = new File(selectDirText.getText());
//                try {
//                    zipFile.addFolder(fileDir,zipParameters);
//                    System.out.println("压缩成功");
//                } catch (ZipException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }

}
