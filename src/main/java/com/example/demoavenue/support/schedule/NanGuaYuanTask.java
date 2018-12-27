package com.example.demoavenue.support.schedule;

import com.example.demoavenue.utils.HttpUtil;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class NanGuaYuanTask extends AbstractTask {

    public static void main(String[] args) {
        final String page = HttpUtil.getPage("http://www.kan2008.com/thread-305248-1-1.html");

        try(final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/oker/Documents/devote/scratch/nanguayuan/waste/test.html")))){
            bw.write(page);
        }catch(Exception e){

        }
        System.out.println("写入完成");
    }

    @Override
    public String getPageUrl(int pageNum) {
        return null;
    }

    @Override
    public List<String> parseImgUrl(List<String> contentLines) {
        return null;
    }

    @Override
    public String getLocalPath(String url) {
        return null;
    }
}
