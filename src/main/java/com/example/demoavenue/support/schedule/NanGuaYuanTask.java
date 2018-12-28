package com.example.demoavenue.support.schedule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class NanGuaYuanTask extends AbstractTask {
    
    public static String domain = "http://www.kan2008.com/";
    public static String localDir = "/Users/oker/Documents/devote/scratch/nanguayuan/";

    public static void main(String[] args) {

        new NanGuaYuanTask().execute(0,0);
        
    }

    @Override
    public String getPageUrl(int pageNum) {
        return domain + "thread-305248-1-1.html";
    }

    @Override
    public List<String> parseImgUrl(List<String> contentLines) {

        List<String> imgUrlList = new ArrayList<>();

        final StringBuilder sb = new StringBuilder();
        contentLines.stream().forEach(x -> sb.append(x));
        final Document document = Jsoup.parse(sb.toString());
        final Elements imgs = document.select("ignore_js_op > .zoom");

        imgs.stream().forEach(img -> {
            // 解析属性
            final String imgPath = domain + img.attr("file");
            final String identityNo = img.attr("aid");
            imgUrlList.add(imgPath);
        });

        return imgUrlList;
    }

    @Override
    public String getLocalPath(String url) {
        final int suffixIndex = url.lastIndexOf(".");
        final int separatorIndex = url.lastIndexOf("/");
        String imgName = url.substring(separatorIndex);
        String localPath = localDir + imgName;
        return localPath;
    }
}
