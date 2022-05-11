package com.yichen.casetest.test.socket;

import java.net.Socket;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/7 9:44
 * @describe socket相关研究
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            Socket socket = null;
            socket.setKeepAlive(true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
