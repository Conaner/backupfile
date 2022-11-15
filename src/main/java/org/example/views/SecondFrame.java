package org.example.views;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.example.core.base.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class SecondFrame extends BaseFrame {
    private static String userText;
    boolean select_flag;
    public static void initAndShow() {
        SecondFrame frame = new SecondFrame(userText);
        frame.setVisible(true);
    }

    public SecondFrame(String userText) throws HeadlessException {
        super("选择压缩与解压");
        SecondFrame.userText = userText;
    }

    @Override
    public void initView() {
        super.initView();

        Container pane = this.getContentPane();
        pane.setLayout(null);

        JLabel selectDir1 = new JLabel("路径下文件全压缩:");
        selectDir1.setBounds(10, 50-30, 120, 25);
        pane.add(selectDir1);
        JLabel selectDir2 = new JLabel("路径下指定后缀压缩:");
        selectDir2.setBounds(10, 80-30, 120, 25);
        pane.add(selectDir2);
        JLabel selectDir3 = new JLabel("请输入文件格式：");
        selectDir3.setBounds(10, 80, 120, 25);
        pane.add(selectDir3);
        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JTextField selectDirText1 = new JTextField(20);
        selectDirText1.setBounds(140, 50-30, 165, 25);
        pane.add(selectDirText1);
        JTextField selectDirText2 = new JTextField(20);
        selectDirText2.setBounds(140, 80-30, 165, 25);
        pane.add(selectDirText2);
        JTextField inputText = new JTextField(20);
        inputText.setBounds(140, 80, 165, 25);
        pane.add(inputText);

        selectDirText1.setText("E:\\test_dir");
        selectDirText2.setText("");
        inputText.setText(".doc");

        JButton selectFileButton1 = new JButton("选择路径");
        selectFileButton1.setBounds(100 + 190 + 30, 50-30, 90 , 25);
        selectFile(pane, selectDirText1, selectFileButton1);
        JButton selectFileButton2 = new JButton("选择路径");
        selectFileButton2.setBounds(100 + 190 + 30, 80-30, 90, 25);
        selectFile(pane, selectDirText2, selectFileButton2);

        String backupFilepathDir = "E:\\DataBackup\\" + userText;
        File folder = new File(backupFilepathDir);
        if (!folder.exists() && !folder.isDirectory())
            folder.mkdirs();
//            System.out.println("创建文件夹");
//        } else {
//            System.out.println("文件夹已存在");
//        }

        JButton zipButton = new JButton("压缩");
        zipButton.setBounds(10, 120, 80, 25);
        pane.add(zipButton);
        zipButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("压缩中...");
                Date date = new Date();
                String Str_data = date.toString().replaceAll("[ :]", "_");
                String final_filename = backupFilepathDir + "\\" + Str_data + ".zip";
                select_flag = true;
                InputCodeFrame frame = new InputCodeFrame(selectDirText1.getText(),selectDirText2.getText(),inputText.getText(),final_filename,"","",select_flag);
                try {
//                    if (!Objects.equals(selectDirText1.getText(), "")){
////                        System.out.println("here");
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    ZipFileWithPwd.zipFile_dir(selectDirText1.getText(),final_filename,file_password);
//                                } catch (ZipException ex) {
//                                    throw new RuntimeException(ex);
//                                }
//                            }
//                        }).start();
//                    }
//                    else if (!Objects.equals(selectDirText2.getText(), "")){
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    String extension = inputText.getText();
//                                    File file =new File(selectDirText2.getText());
//                                    File[] listFiles = file.listFiles((d, s) -> s.toLowerCase().endsWith(extension));
//                                    ZipFileWithPwd.zipFiles(listFiles,final_filename,file_password);
//                                } catch (ZipException ex) {
//                                    throw new RuntimeException(ex);
//                                }
//                            }
//                        }).start();
//                 }
//                    ZipParameters zipParameters = new ZipParameters();
//                    zipParameters.setEncryptFiles(true);
//                    zipParameters.setEncryptionMethod(EncryptionMethod.AES);
//                    zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
//                    Scanner in=new Scanner(System.in);
//                    System.out.println("请输入待压缩包密码：");
//                    file_password = in.next().trim();
//                    ZipFile zipFile = new ZipFile(final_filename, file_password.toCharArray());
//                    zipFile.addFile(final_filename, zipParameters);


//                    zipFile.setPassword(in.next().trim().toCharArray());
                    String file = Str_data + ".zip";
                    removeFileFromZip.removeFile(new File(final_filename),file);

//                    System.out.println("压缩成功");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }




//                Date date = new Date();
//                String Str_data = date.toString().replaceAll("[ :]", "_");
//
//                String file = backupFilepathDir + "\\" + Str_data + ".zip";
//
//                ZipParameters zipParameters = new ZipParameters();
//                zipParameters.setEncryptFiles(true);
//                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
//                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
//
//
//                ZipFile zipFile = new ZipFile(file, file_password.toCharArray());
//                File fileDir = new File(selectDirText.getText());
//                try {
//                    zipFile.addFolder(fileDir, zipParameters);
//                    System.out.println("压缩成功");
//                } catch (ZipException ex) {
//                    ex.printStackTrace();
//                }
             //   return Str_data;
            }

        });

        JLabel selectDir_ = new JLabel("待解压文件路径:");
        selectDir_.setBounds(10, 150, 100, 25);
        pane.add(selectDir_);
        JTextField selectDirText_ = new JTextField(20);
        selectDirText_.setBounds(140, 150, 165, 25);
        pane.add(selectDirText_);
        JButton selectFileButton_ = new JButton("选择路径");
        selectFileButton_.setBounds(100 + 190 + 30, 150, 90, 25);
        selectFile(pane, selectDirText_, selectFileButton_);

        JLabel selectDir__ = new JLabel("将文件解压到:");
        selectDir__.setBounds(10, 180, 80, 25);
        pane.add(selectDir__);
        JTextField selectDirText__  = new JTextField(20);
        selectDirText__.setBounds(140, 180, 165, 25);
        pane.add(selectDirText__);
        JButton selectFileButton__ = new JButton("选择路径");
        selectFileButton__.setBounds(100 + 190 + 30, 180, 90, 25);
        selectFile(pane, selectDirText__, selectFileButton__);

        JButton unzipButton = new JButton("解压");
        unzipButton.setBounds(10, 210, 80, 25);
        pane.add(unzipButton);
        unzipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (ZipFile zipFile = new ZipFile(selectDirText_.getText())) {
                    try {
                        if (!zipFile.isEncrypted()) {
                            System.out.println("解压中...");
                            zipFile.extractAll(selectDirText__.getText());
                            System.out.println("解压成功");
                        } else {
                            select_flag = false;
                            InputCodeFrame frame = new InputCodeFrame("", "", "", "", selectDirText_.getText(), selectDirText__.getText(), select_flag);
                        }
                    } catch (ZipException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//                    System.out.println("解压中...");
//                    ZipFile zipFile = new ZipFile(selectDirText_.getText());
//                    checkEncrypted(zipFile);
//                    zipFile.extractAll(selectDirText__.getText());
////                    FileZip.ZipUncompress(selectDirText_.getText(), selectDirText__.getText());
//                    System.out.println("解压成功");
            }
        });

    }

//    private void checkEncrypted(ZipFile zip) throws ZipException {
//        Scanner in=new Scanner(System.in);
//        if (zip.isEncrypted()) {
//            System.out.println("文件"+zip.getFile().getName()+"有密码！");
//            System.out.println("请输入密码：");
//            zip.setPassword(in.next().trim().toCharArray());
//        }
//        in.close();
//    }
    private void selectFile(Container pane, JTextField selectDirText, JButton selectFileButton) {
        pane.add(selectFileButton);
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择文件夹");
                File file = jfc.getSelectedFile();
                selectDirText.setText(file.getAbsolutePath());
//                if (file.isDirectory()) {
//                    System.out.println("文件夹:" + file.getAbsolutePath());
//                    selectDirText.setText(file.getAbsolutePath());
//                } else if (file.isFile()) {
//                    System.out.println("请选择文件夹");
//                }
            }
        });
    }
}

