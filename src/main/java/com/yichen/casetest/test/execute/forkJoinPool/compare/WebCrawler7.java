package com.yichen.casetest.test.execute.forkJoinPool.compare;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 16:39
 * @describe
 */
 class WebCrawler7 implements LinkHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    //    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<>());
    private String url;
    private ForkJoinPool mainPool;
    private String desc;

    public String getDesc() {
        return desc;
    }

    @Override
    public void saveResult(String result) {
        this.desc = result;
    }

    protected WebCrawler7(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    protected void startCrawling() {
        mainPool.invoke(new LinkFinderAction(this.url, this));
    }

    @Override
    public void queueLink(String link) throws Exception {

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        LinkFinderAction.t0 = System.nanoTime();
        new WebCrawler7("https://www.infoworld.com/category/java/", 64).startCrawling();
    }
}
