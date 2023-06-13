package com.yichen.casetest.test.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/7 21:55
 * @describe
 */
@Slf4j
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9998, 20);
        AtomicInteger i = new AtomicInteger(0);
        log.info("server begin");
        while (true) {
            //阻塞1
            Socket client = server.accept();
//            log.info("accept client {}", client.getPort());
            Thread t = new Thread(() -> {
                log.info("thread start");
                InputStream in;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        //阻塞2
                        String data = reader.readLine();
                        if (null != data) {
                            System.out.println(data);
                        } else {
                            client.close();
                            break;
                        }
                    }
                    log.info("client break");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.setName("exec-thread" + i.getAndIncrement());
            t.start();
        }
    }

}
