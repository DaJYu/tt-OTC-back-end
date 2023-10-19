package com.ydj.ttswap.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class ServiceSaveFile {

/**
 *  文件上传
 */
    public static boolean singleFileUpload(MultipartFile file, String FileName, String FilePath) {

//        List result = new ArrayList();
//        Map map = new HashMap<>();
//        System.out.println(0);
        System.out.println("*******"+FilePath+FileName);

        if (file.isEmpty()) {
            return true;
        }

//        String fileName = null;
//        try {
//            fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        // 截取上传文件的文件名
//        String uploadFileName = fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.lastIndexOf("."));
        // 截取上传文件的后缀
//        String uploadFileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

//        String uuid = StringHelper.Uuid();
        File dest = new File(FilePath + FileName);// + uploadFileSuffix);

        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
//            dest.setExecutable(false, false);
//            dest.setWritable(true, true);
//            dest.setReadable(true, false);

//            map.put("fileFullName", FileName + uploadFileSuffix);
//            result.add(map);
            return true;

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 删除
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
