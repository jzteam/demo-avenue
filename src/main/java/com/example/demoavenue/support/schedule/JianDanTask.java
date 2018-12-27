package com.example.demoavenue.support.schedule;

import com.example.demoavenue.utils.HttpUtil;
import com.example.demoavenue.utils.MD5Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JianDanTask extends AbstractTask {

    final String urlTemplate = "http://jandan.net/ooxx/page-{pageNum}#comments";

    public static void main(String[] args) {

        final JianDanTask task = new JianDanTask();
        task.execute(10, 50);

    }

    @Override
    public String getPageUrl(int pageNum) {
        return urlTemplate.replace("{pageNum}", pageNum + "");
    }

    @Override
    public List<String> parseImgUrl(List<String> lines) {
        // 拿到img-hash
        List<String> imgHashList = new ArrayList<>();

        final StringBuilder sb = new StringBuilder();
        lines.stream().forEach(x -> sb.append(x));
        final Document document = Jsoup.parse(sb.toString());
        final Elements rows = document.select("#comments > .commentlist .row");


        rows.stream().forEach(row -> {
            final Elements likeTargets = row.select(".jandan-vote > .tucao-like-container > span");
            final Elements unlikeTargets = row.select(".jandan-vote > .tucao-unlike-container > span");
            if (likeTargets == null || likeTargets.size() == 0 || unlikeTargets == null || unlikeTargets.size() == 0) {
                return;
            }
            // 支持数量
            int like = Integer.parseInt(likeTargets.get(0).html());
            int unlike = Integer.parseInt(unlikeTargets.get(0).html());
            // 喜欢超过100人，而且占比达到60%以上
            if (like > 100 && (float) like / (like + unlike) > 0.6) {
                System.out.println("like=" + like + ", unlike=" + unlike);
                final Elements hashes = row.select(".text .img-hash");
                if (hashes == null || hashes.size() == 0) {
                    return;
                }
                hashes.stream().forEach(x -> imgHashList.add(x.html()));
            }
        });
        if (CollectionUtils.isEmpty(imgHashList)) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        final Base64.Decoder decoder = Base64.getDecoder();

        imgHashList.stream().forEach(x -> {
            try {
                result.add(new String(decoder.decode(x), "UTF-8"));
            } catch (Exception e) {

            }
        });

        return result;
    }

    @Override
    public String getLocalPath(String url) { 
        int last = url.lastIndexOf(".");
        String imgName = url.substring(url.length() - 14, last) + "_" + new Date().getTime() + url.substring(last);
        String localPath = "/Users/oker/Documents/test/jiandan/" + imgName;
        return localPath;
    }

    // =================================================================================================================
    // ============= 曾经以为需要加密解密，后来发现这都是无用的 ==============================================================
    // =================================================================================================================
    /**
     * 曾经以为需要加密解密
     * @param imgHash
     * @param constant
     * @return
     */
    public static String parseJianDanImgUrl(String imgHash, String constant) {

        int r = 4;
        constant = MD5Util.MD5EncodeWithUtf8(constant);
        String p = MD5Util.MD5EncodeWithUtf8(constant.substring(0, 16));
        String o = MD5Util.MD5EncodeWithUtf8(constant.substring(16, 32));

        String d = imgHash;
        String m = imgHash.substring(0, r);
        String c = p + MD5Util.MD5EncodeWithUtf8(p + m);

        imgHash = imgHash.substring(r);
        String l;
        try {
            l = new String(Base64.getDecoder().decode(imgHash), "UTF-8");
        } catch (Exception e) {
            l = "";
        }

        int[] k = new int[256];
        for (int g = 0; g < 256; g++) {
            k[g] = g;
        }

        int[] b = new int[256];
        for (int g = 0; g < 256; g++) {
            b[g] = c.charAt(g % c.length());
        }

        for (int h = 0, g = 0; h < 256; h++) {
            g = (g + k[h] + b[h]) % 256;
            int tmp = k[h];
            k[h] = k[g];
            k[g] = tmp;
        }

        String u = "";
        String[] ls = l.split("");
        for (int q = 0, g = 0, h = 0; h < ls.length; h++) {

            q = (q + 1) % 256;
            g = (g + k[q]) % 256;
            int tmp = k[q];
            k[q] = k[g];
            k[g] = tmp;

            u += (char) (ls[h].toCharArray()[0] ^ k[(k[q] + k[g]) % 256]);
        }
        // 原来上面一堆都是假的
        u = String.valueOf(Base64.getDecoder().decode(d));
        return u;
    }

    private void parseOld() {
        final List<String> lines = HttpUtil.getLines("http://jandan.net/ooxx/page-50#comments");

        // 拿到img-hash
        List<String> imgHashList = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        lines.stream().forEach(x -> sb.append(x));
        final Document document = Jsoup.parse(sb.toString());
        final Elements elements = document.select("#comments > .commentlist .text > p > .img-hash");
        elements.stream().forEach(x -> imgHashList.add(x.html()));

        // 拿到js
        String jsUrl = "";
        final Pattern pattern = Pattern.compile(".*(cdn.jandan.net/static/min/.+\\.[0-9]{8}\\.js).*");
        String preLine = "";
        for (String x : lines) {
            final Matcher matcher = pattern.matcher(x);
            if (!"<!--".equalsIgnoreCase(preLine.trim()) && matcher.matches()) {
                jsUrl = matcher.group(1);
                System.out.println("preLine=" + preLine);
                System.out.println(x);
                break;
            }
            preLine = x;
        }
        System.out.println("jsUrl=" + jsUrl);
        if (StringUtils.isEmpty(jsUrl)) {
            System.out.println("没拿到jsUrl");
            return;
        }

        // 解析出常量
        String jsConstant = "";
        final String jsText = HttpUtil.getPage("http://" + jsUrl);
        final int offset = jsText.indexOf("jandan_load_img") + 93;
        final String targetScope = jsText.substring(offset, offset + 73);
        System.out.println("targetScope=" + targetScope);
        final String[] split = targetScope.split("\"");
        jsConstant = split[1];
        if (StringUtils.isEmpty(jsConstant)) {
            System.out.println("没拿到jsConstant");
            return;
        }
    }
}
