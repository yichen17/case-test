package com.yichen.casetest.test.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/7 21:53
 * @describe
 */
@Slf4j
public class Client {

    static String ip = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress(ip, 9998);
        IntStream.range(20000, 21000).forEach(i -> {
            try {
                SocketChannel client = SocketChannel.open();
                client.bind(new InetSocketAddress(ip, i));
                client.connect(serverAddr);
                log.info("client:{} connected", i);
                clients.add(client);

            } catch (IOException e) {
                log.error("IOException {}",i);
                e.printStackTrace();
            }
        });
        log.info("clients size {}", clients.size());
        //阻塞主线程
        System.in.read();
    }

}
