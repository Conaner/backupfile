package org.example.core.base;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class removeFileFromZip {
        public static void removeFile(File file, String removeDir) throws Exception {
                try (ZipFile zipFile = new ZipFile(file)) {
                        // 遍历压缩文件中所有的FileHeader, 将指定删除目录下的子文件名保存起来
                        @SuppressWarnings("unchecked")
                        List<FileHeader> headersList = zipFile.getFileHeaders();
                        ArrayList<String> removeHeaderNames = new ArrayList<String>();
                        for (FileHeader subHeader : headersList) {
                                String subHeaderName = subHeader.getFileName();
                                if (subHeaderName.contains(removeDir)) {
//                                        log.info("需要删除的文件名称为：" + subHeaderName);
                                        removeHeaderNames.add(subHeaderName);
                                }
                        }
                        // 遍历删除指定目录下的所有子文件(所有子文件删除完毕，该目录自动删除)
                        for (String headerNameString : removeHeaderNames) {
                                zipFile.removeFile(headerNameString);
                        }
                }
        }

//        public static void main(String[] args) {
//                try {
//                        removeFile(new File("E:\\test.zip"),"testdoc");
//                } catch (Exception e) {
//                        throw new RuntimeException(e);
//                }
//        }
}
