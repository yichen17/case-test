package com.yichen.casetest.config.position;

import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/13 10:48
 * @describe 爬取网页code码 存储
 * https://www.mca.gov.cn/article/sj/xzqh/1980/
 * 放弃，每个几个页面数据展示的样式就变，有调坐牢
 */
@Slf4j
public class CrawlDataStore {

    private static final String yearPrefix = "https://www.mca.gov.cn";

    private static final String totalPageUrl = "https://www.mca.gov.cn/article/sj/xzqh/1980/?";

    private static final String[] totalPage = {"", "2", "3"};

    public static void main(String[] args) {
//        getTotal();
//        code 540422 name 米林县  错误
        getYear("/article/sj/xzqh/1980/201611/20161115002410.shtml");
//        getCode("https://www.mca.gov.cn/article/sj/xzqh/1980/201903/201903011447.html");

        // 批量处理未成功的
//        batchDeal();
    }

    private static void batchDeal(){
        try {
            FileInputStream fileInputStream = new FileInputStream("G:\\base\\case-test\\src\\main\\resources\\config\\waitStoreData.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String str = null;
            while((str = bufferedReader.readLine()) != null)
            {
                getYear(str);
            }
            fileInputStream.close();
            bufferedReader.close();
        }
        catch (IOException e){
            log.error("执行出错 {}", e.getMessage(), e);
        }
    }

    private static void getTotal(){
        try {
            for (String s :totalPage){
                Connection connect = HttpConnection.connect(totalPageUrl + s);
                connect.timeout(3000);
                connect.header("Connection", "close");
                connect.validateTLSCertificates(false);
                Document doc = connect.timeout(5000).get();
                List<Element> yearInfos = doc.getElementsByClass("artitlelist");
                for (Element yearInfo : yearInfos){
                    // 每一年的code码映射表地址
                    String yearUrl = yearInfo.attributes().get("href");
                    String title = yearInfo.attributes().get("title");
                    log.info("爬取年行政代码 title {} href {}", title, yearUrl);
//                if (title.contains("中华人民共和国行政区划代码")){
//                    getYear(yearUrl);
//                }
                }
            }
        }
        catch (Exception e){
            log.error("爬取code码出现错误 {}", e.getMessage(), e);
        }
    }

    private static void getYear(String subUrl){
        // 获取页面数据
        Document yearDoc = getDocument(yearPrefix + subUrl);
        Element yearCodeInfo = yearDoc.getElementsByClass("content").get(0);
        // 获取目标元素
        Elements elements = yearCodeInfo.getElementsByAttributeValue("style", "TEXT-ALIGN: justify; TEXT-INDENT: 2em");
        if (elements.size() == 0){
            elements = yearCodeInfo.getElementsByAttributeValue("style", "TEXT-INDENT: 2em");
            String name = ((TextNode)(elements.get(0).childNode(0).childNode(0))).text();
            log.info("特殊年份code {}", name);
            if (name.contains("代码")){
                getCode(elements.get(0).childNode(0).attributes().get("href"), name);
            }
        }
        else {
            // 常规 1
            for (Element element : elements){
                Node node = element.childNode(0);
                if (element.childNodeSize() == 1 && element.childNode(0).childNodeSize() == 1
                        && element.childNode(0).childNode(0) instanceof TextNode){
                    TextNode remark = (TextNode) element.childNode(0).childNode(0);
                    String codeHref = element.childNode(0).attributes().get("href");
                    log.info("爬取年份的地区code码 remark {} url {}", remark.text(), codeHref);
                    // 筛选数据，如果是是代码则继续筛选
                    if (remark.text().contains("代码")){
                        getCode(codeHref, remark.text());
                    }
                }
            }
        }
    }

    private static void getCode(String url, String fileName){
        Map<String, String> codePositionMap = new HashMap<>(4096);
        // 获取页面数据
        Document codeDoc = getDocument(url);
        log.info("data\n{}", codeDoc.data());
        Elements elements = codeDoc.getElementsByTag("tr");
        for (Element element : elements){
            Elements tdElements = element.getElementsByTag("td");
            // 获取数据，如果为2表示目标code
            if (tdElements.size() == 2){
                String code = ((TextNode)tdElements.get(0).childNode(0)).text();
                String name = ((TextNode)tdElements.get(1).childNode(0)).text();
                log.info("code {} name {}", code, name);
                codePositionMap.put(code,name);
            }
            // 适配
            else if(tdElements.size() > 6 && tdElements.get(1).childNodeSize() > 0){
                String code = ((TextNode)tdElements.get(1).childNode(0)).text();
                // 判断是否为 title
                if (!code.matches("[0-9]*")){
                    continue;
                }
                String name ;
                if (tdElements.get(2).childNodeSize() == 1){
                    name = ((TextNode)tdElements.get(2).childNode(0)).text();
                }
                else {
                    name = ((TextNode)tdElements.get(2).childNode(1)).text();
                }
                log.info("code {} name {}", code, name);
                codePositionMap.put(code,name);
            }

        }
        try {
            FileWriter fileWriter = new FileWriter("F:\\test_store\\codePosition\\" + fileName);
            fileWriter.append(FastJsonUtils.toJson(codePositionMap));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            log.error("写入文件出错 {} {}", fileName, e.getMessage(), e);
        }
    }


    private static Document getDocument(String url){
        Document doc = null;
        try {
            Connection connect = HttpConnection.connect(url);
            connect.timeout(3000);
            connect.header("Connection", "close");
            connect.validateTLSCertificates(false);
            doc = connect.timeout(5000).get();
        }
        catch (Exception e){
            log.error("请求接口错误 {}", e.getMessage(), e);
        }
        return doc;
    }

}
