package org.example;

import net.lingala.zip4j.model.ZipParameters;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.*;
import java.util.zip.ZipEntry;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.*;

import java.io.File;

public class FileZip {
    /**
     * zip文件压缩
     * @param inputFile 待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */
    private static final String file_password = "password";
    //  a,b
    //文件名
//    public static String dirNames = "";
//    //文件扩展名
//    public static String exts = "";
//    public static String nameMatch = "";
    public static void ZipCompress(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File input = new File(inputFile);
        compress(out, bos, input,null);//,0);
        out.hashCode();
        bos.close();
        out.close();
    }
    /**
     * @param name 压缩文件名，可以写为null保持默认
     */
    //递归压缩
//    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name,int level)
    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        // 路径/类型/名字

        //如果路径为目录（文件夹）
        if (input.isDirectory()) {

//            if (level == 1){
//                List<String> dirnameList = Arrays.stream(dirNames.split(",")).toList();
//                // 不在数组中
//                if (dirnameList.size() > 0 && !dirnameList.contains(input.getName())) {
//                    return;
//                }
//            }

            //取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();

            if (flist.length == 0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            {
                out.putNextEntry(new ZipEntry(name + "/"));
            } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (File file : flist) {
                    compress(out, bos, file, name + "/" + file.getName());//,level+1);
                }
            }
        } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
//            String fileName = input.getName();
//            String pureFileName = fileName;
//            String ext = "";
//
//            int lastIndexOf = fileName.lastIndexOf(".");
//            if(lastIndexOf > -1){
//                pureFileName = fileName.substring(0,lastIndexOf);
//                ext = fileName.substring(0,lastIndexOf);
//            }
//
//            List<String> extList = Arrays.stream(exts.split(",")).toList();
//            // 不在数组中
//            if (extList.size() > 0 && !extList.contains(input.getName())) {
//                return;
//            }
////       过滤文件
//            try{
//                Pattern p = Pattern.compile(nameMatch);
//                Matcher m = p.matcher(pureFileName); // 获取 matcher 对象
//
//                if (!m.matches()) {
//                    return;
//                }
//            }catch (PatternSyntaxException e){
//                e.printStackTrace();
//            }


            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len=-1;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf,0,len);
            }
            bis.close();
            fos.close();
        }

    }

    public static void zipFiles(File[] srcfile, String zipfile) throws IOException {
        byte[] buf = new byte[1024];

            //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        BufferedOutputStream bos = new BufferedOutputStream(out);
        for (File file : srcfile) {
            try {
                compress(out, bos, file, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        out.hashCode();
        bos.close();
        out.close();
    }

    /**
     * zip解压
     * @param inputFile 待解压文件名
     * @param destDirPath  解压路径
     */

    public static void ZipUncompress(String inputFile,String destDirPath) throws Exception {
        File srcFile = new File(inputFile);//获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        //开始解压
        //构建解压输入流
        ZipInputStream zIn = new ZipInputStream(new FileInputStream(srcFile));
        ZipEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();//创建此文件的上级目录
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
    }

}
