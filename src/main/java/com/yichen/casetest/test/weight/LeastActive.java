//package com.yichen.casetest.test.weight;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @author Qiuxinchao
// * @version 1.0
// * @date 2022/7/11 9:33
// * @describe  最小活跃数算法
// *  Dubbo 框架中的 com.alibaba.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance
// *  参考 Ribbon 框架 com.netflix.loadbalancer 包下面的 BestAvailableRule 最小活跃数算法实现类。
// */
//// 节点类：用于封装集群中的每个节点
//class Server {
//    private String IP;
//    private AtomicInteger active;
////    private Integer weight;
//
//    public Server(){}
//    public Server(String IP,int active) {
//        this.IP = IP;
//        // 将外部传递的活跃数作为默认活跃数
//        this.active = new AtomicInteger(active);
//    }
//
//    public String getIP() {
//        // 每分发一个请求时自增一次活跃数
//        active.incrementAndGet();
//        return IP;
//    }
//
//    public AtomicInteger getActive() {
//        return active;
//    }
//}
//
//// 集群类：用于模拟集群节点列表
//class Servers {
//    // 活跃度衰减器
//    public static void attenuator(){
//        new Thread(()->{
//            // 遍历集群中的所有节点
//            for (Server server : Servers.SERVERS) {
//                // 如果活跃度不为0
//                if (server.getActive().get() != 0){
//                    // 则自减一个活跃度
//                    server.getActive().getAndDecrement();
//                }
//            }
//            try {
//                // 每隔 2 秒中衰减一次活跃度
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    // 模拟的集群节点信息，活跃数最开始默认为0
//    public static List<Server> SERVERS = Arrays.asList(
//            new Server("44.120.110.001:8080",0),
//            new Server("44.120.110.002:8081",0),
//            new Server("44.120.110.003:8082",0)
//    );
//}
//
//// 最小活跃数算法实现类
//public class LeastActive {
//
//    public static String getServer(){
//        // 初始化最小活跃数和最小活跃数的节点
//        int leastActive = Integer.MAX_VALUE;
//        Server leastServer = new Server();
//        // 遍历集群中的所有节点
//        for (Server server : Servers.SERVERS) {
//            // 找出活跃数最小的节点
//            if (leastActive > server.getActive().get()){
//                leastActive = server.getActive().get();
//                leastServer = server;
//            }
//        }
//
//        // 返回活跃数最小的节点IP
//        return leastServer.getIP();
//    }
//
//    public static void main(String[] args){
//        Servers.attenuator();
//        for (int i = 1; i <= 10; i++){
//            System.out.println("第"+ i + "个请求：" + getServer());
//        }
//    }
//}
