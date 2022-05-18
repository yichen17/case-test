package com.yichen.casetest.test.stream;

import lombok.extern.slf4j.Slf4j;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/17 12:34
 * @describe 测试流相关
 */
@Slf4j
public class TestStream {

    public static void main(String[] args) {
        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream();
            out.connect(in);
            out.write(74);
            System.out.println("using read() : " + (char)in.read());
            out.write(75);
            System.out.println("using read() : " + (char)in.read());
            out.write(79);
            System.out.println("using read() : " + (char)in.read());
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
