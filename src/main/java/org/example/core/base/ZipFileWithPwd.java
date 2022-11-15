package org.example.core.base;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ZipFileWithPwd {
    public static void zipFile_dir(String dir, String final_filename,String file_password) throws ZipException {
        // 生成的压缩文件
        try (ZipFile zipFile = new ZipFile(final_filename)) {
            ZipParameters parameters = new ZipParameters();
            // 压缩方式
            parameters.setCompressionMethod(CompressionMethod.DEFLATE);
            // 压缩级别
            parameters.setCompressionLevel(CompressionLevel.NORMAL);
            parameters.setEncryptFiles(!Objects.equals(file_password, ""));
            parameters.setEncryptionMethod(EncryptionMethod.AES);
            parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

//        InputCodeFrame fr = new InputCodeFrame();
//        String password = fr.init();
//        Scanner in=new Scanner(System.in);
//        System.out.println("请输入待压缩包密码：");
            zipFile.setPassword(file_password.toCharArray());
//        zipFile.setPassword(password.toCharArray());
//        zipFile.setPassword("111".toCharArray());
            // 要打包的文件夹
            File currentFile = new File(dir);
            File[] fs = currentFile.listFiles();
            // 遍历test文件夹下所有的文件、文件夹
            assert fs != null;
            for (File f : fs) {
                if (f.isDirectory()) {
                    zipFile.addFolder(f, parameters);
                } else {
                    zipFile.addFile(f, parameters);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void zipFiles(File[] files, String final_filename,String file_password) throws ZipException {
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(final_filename);
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        parameters.setEncryptFiles(!Objects.equals(file_password, ""));
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
//
//        Scanner in=new Scanner(System.in);
//        System.out.println("请输入待压缩包密码：");
        zipFile.setPassword(file_password.toCharArray());

        // 遍历test文件夹下所有的文件、文件夹
        for (File f : files) {
            if (f.isDirectory()) {
                zipFile.addFolder(f, parameters);
            } else {
                zipFile.addFile(f, parameters);
            }
        }
//        zipFile.removeFile(final_filename);
    }
//    public static void main(String[] args) throws ZipException {
//        zipFile();
//    }
}
