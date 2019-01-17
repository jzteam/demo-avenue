package com.example.demoavenue.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
@Slf4j
public class Fileutil {

    /**
     * 下载文件到本地
     * @param fileUrl
     * @param localPath
     */
    public static void download(String fileUrl, String localPath) {
        System.out.println("开始下载图片："+fileUrl);
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL(fileUrl);
            dataInputStream = new DataInputStream(url.openStream());

            fileOutputStream = new FileOutputStream(new File(localPath));
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
            log.error("download MalformedURLException 异常：fileUrl={}, localPath={}", fileUrl, localPath);
        } catch (IOException e) {
            log.error("download IOException 异常：fileUrl={}, localPath={}", fileUrl, localPath);
        }finally{
            if(dataInputStream != null){
                try {
                    dataInputStream.close();
                }catch (Exception e){}
            }
            if(fileOutputStream != null){
                try{
                    fileOutputStream.close();
                }catch (Exception e){}
            }
        }
    }

    public static void main(String[] args) {
//        download("http://www.kan2008.com/forum.php?mod=attachment&aid=MjI1MTQwfDM0ZTlkZmIxfDE1NDU5NjQ2NzZ8MHwzMDUyNDg%3D&nothumb=yes", "/Users/oker/Documents/devote/scratch/nanguayuan/test.jpg");
        download("http://www.kan2008.com/data/attachment/forum/201812/27/121820nkdrhhj1yuws0fdz.jpg", "/Users/oker/Documents/devote/scratch/nanguayuan/test.jpg");
        System.out.println("下载完成");
    }
}
