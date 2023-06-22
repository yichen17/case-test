package com.yichen.casetest.test.execute.forkJoinPool.compare;


import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author Qiuxinchao
 * @date 2023/6/19 16:38
 * @describe
 */
@Slf4j
 class WebCrawler6 implements LinkHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    //    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<String>());
    private String url;
    private ExecutorService execService;

    public static volatile boolean cal = false;
    public static volatile List<Long> result = Collections.synchronizedList(new ArrayList<>());
    public static CountDownLatch countDownLatch;

    @Override
    public void saveResult(String result) {

    }

    protected WebCrawler6(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public void queueLink(String link) throws Exception {
        startNewThread(link, false);
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private void startNewThread(String link, boolean parent) throws Exception {
        execService.execute(new LinkFinder(link, this, parent));
    }

    protected void startCrawling() throws Exception {
        startNewThread(this.url, true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

//        List<Long> items = Arrays.asList(11L, 22L, 1L, 3L);
//        log.info("{}", items.stream().reduce(Long::sum).get());

//        WebCrawler6 webCrawler6 = new WebCrawler6("http://www.javaworld.com", 64);
//        int num = 20;
//        for (int i=0; i<num; i++){
//            countDownLatch = new CountDownLatch(1);
//            if (i == 10){
//                cal = true;
//            }
//            webCrawler6.startCrawling();
//            System.gc();
//            countDownLatch.await();
//            webCrawler6.visitedLinks.clear();
//        }
//        StringUtils.divisionLine();
//        log.info("max => {}", result.stream().max(Long::compare));
//        log.info("min => {}", result.stream().min(Long::compare));
//        log.info("average => {}", result.stream().reduce(Long::sum).orElse(0L) / result.size());
//        webCrawler6.execService.shutdown();

//        new WebCrawler6("http://www.baidu.com", 64).startCrawling();
        new WebCrawler6("https://www.infoworld.com/category/java/", 64).startCrawling();

    }
}
