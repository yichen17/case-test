package com.yichen.casetest.test.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/17 21:35
 * @describe 测试  socket
 *      socket 以使用方的维度，像服务端发送数据 =》  往socket.getOutputStream.write
 *      从服务端接收数据  =》  socket.getInputStream.read()
 */
@Slf4j
public class TestSocket {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("www.baidu.com", 80);
            InputStream is = clientSocket.getInputStream();
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
//            pw.println("GET / HTTP/1.0");
            pw.println();
            pw.flush();
            byte[] buffer = new byte[1024];
            int read;
            while((read = is.read(buffer)) != -1) {
                String output = new String(buffer, 0, read);
                System.out.print(output);
                System.out.flush();
            };
            clientSocket.close();
        }
        catch (Exception e){
            log.error("错误 {}",e.getMessage(),e);
        }
    }
}
