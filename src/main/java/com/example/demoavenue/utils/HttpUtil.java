package com.example.demoavenue.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Http请求相关工具
 */
@Slf4j
public class HttpUtil {

    /**
     * 根据url获取页面html
     *
     * @param url
     * @return
     */
    public static String getPage(String url) {

        final List<String> lines = getLines(url);
        if(lines == null || lines.size() == 0){
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        lines.stream().forEach(x->{
            sb.append(x);
            sb.append("\n");
        });

        return sb.toString();
    }
    
    public static List<String> getLines(String url){
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36"));
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String charset = "UTF-8";
            if(entity.getContentType() != null){
                final String[] split = entity.getContentType().getValue().split("=");
                if(split != null && split.length > 1){
                    charset = split[1].trim();
                }
            }

            final BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));

            final List<String> lines = new ArrayList<>();
            String line;
            while((line=br.readLine()) != null){
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            log.error("getLines error",e);
        }
        return null;
    }

    public static void main(String[] args) {
        final String page = HttpUtil.getPage("http://jandan.net/ooxx/page-48#comments");
        System.out.println(page);

        try(final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/oker/Documents/test/jiandan.html")))){
            bw.write(page);
        }catch(Exception e){
            
        }
        System.out.println("写入完成");
    }
}
