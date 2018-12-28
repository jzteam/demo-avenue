package com.example.demoavenue.support.schedule;

import com.example.demoavenue.utils.Fileutil;
import com.example.demoavenue.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Slf4j
public abstract class AbstractTask {

    public static void main(String[] args) {
        final String page = HttpUtil.getPage(NanGuaYuanTask.domain+"thread-305248-1-1.html");

        try(final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(NanGuaYuanTask.localDir+"waste/test.html")))){
            bw.write(page);
        }catch(Exception e){

        }
        System.out.println("写入完成");
    }

    public void execute(int startPage, int endPage) {

        final long start = System.currentTimeMillis();
        log.info("{} 任务开始：页码从 {} 到 {}", this.getClass().getSimpleName(), startPage, endPage);

        for (int i = startPage; i <= endPage; i++) {
            final String url = this.getPageUrl(i);
            log.info("第 {} 页面链接：{}", i, url);
            final List<String> lines = HttpUtil.getLines(url);
            final List<String> imgUrlList = this.parseImgUrl(lines);
            log.info("第 {} 页面获得图片数量：{}", i, imgUrlList!=null?imgUrlList.size():0);
            this.download(imgUrlList);
            log.info("第 {} 页面图片下载完成");
        }

        log.info("{} 任务结束：页码从 {} 到 {}，耗时 {} 秒", this.getClass().getSimpleName(), startPage, endPage, (System.currentTimeMillis() - start)/1000);

    }

    private void download(List<String> imgUrlList){
        if(CollectionUtils.isEmpty(imgUrlList)){
            return;
        }

        imgUrlList.stream().forEach(x -> {
            // 路径
            String localPath = this.getLocalPath(x);
            // 下载
            Fileutil.download(x, localPath);
        });
    }
    
    public abstract String getPageUrl(int pageNum);

    public abstract List<String> parseImgUrl(List<String> contentLines);
    
    public abstract String getLocalPath(String url);
}
