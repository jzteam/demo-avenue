package com.example.demoavenue.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Fileutil {

    /**
     * 下载文件到本地
     * @param fileUrl
     * @param localPath
     */
    public static void download(String fileUrl, String localPath) {
        System.out.println("开始下载图片："+fileUrl);
        try {
            URL url = new URL(fileUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            System.out.println("download MalformedURLException 异常：fileUrl="+fileUrl+", localPath="+localPath);
        } catch (IOException e) {
            System.out.println("download IOException 异常：fileUrl="+fileUrl+", localPath="+localPath);
        }
    }
}
