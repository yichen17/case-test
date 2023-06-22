package com.yichen.casetest.test.execute.forkJoinPool.compare;

import lombok.extern.slf4j.Slf4j;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 16:38
 * @describe
 */
@Slf4j
 class LinkFinder implements Runnable {

    private String url;
    private LinkHandler linkHandler;
    private boolean parent;
    public  AtomicBoolean end = new AtomicBoolean(false);
    /**
     * Used fot statistics
     */
    protected static long t0 = System.nanoTime();

    public LinkFinder(String url, LinkHandler handler, boolean parent) {
        this.url = url;
        this.linkHandler = handler;
        this.parent = parent;
    }


    @Override
    public void run() {
        getSimpleLinks(url);
    }

    private void getSimpleLinks(String url) {

        // 已经到指定次数了，停止
        if (end.get()){
            return;
        }

        //if not already visited
        if (!linkHandler.visited(url)) {
            try {
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                List<String> urls = new ArrayList<String>();

                for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.getLink().isEmpty()
                            && !linkHandler.visited(extracted.getLink())) {

                        urls.add(extracted.getLink());
                    }

                }
                log.info("now url num {} {}", urls.size(), linkHandler.size());
                //we visited this url
                linkHandler.addVisited(url);

                if (linkHandler.size() == 1500) {
                    // 到指定次数停止
                    if (end.compareAndSet(false, true)){
                        Long cost = System.nanoTime() - t0;
                        log.info(String.format("Time to visit 1500 distinct links = %s, now %s", cost, linkHandler.size()));
//                    if (WebCrawler6.cal){
//                        WebCrawler6.result.add(cost);
//                    }
                    }
                    return;
                }

                for (String l : urls) {
                    linkHandler.queueLink(l);
                }

            } catch (Exception e) {
//                log.error("link finder error {}", e.getMessage(), e);
            }
            finally {
//                if (parent){
//                    WebCrawler6.countDownLatch.countDown();
//                }
            }
        }
    }
}
