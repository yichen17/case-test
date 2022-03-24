package com.yichen.casetest.test.bytebuffer;

import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/23 17:54
 * @describe 研究 MappedByteBuffer
 *   =>  参考链接   https://www.cnblogs.com/swbzmx/p/5992592.html
 */
public class MappedByteBufferTestSuccess {

    public static void main(String[] args) {
        try {
//            File f = File.createTempFile("Test", null);
//            f.deleteOnExit();
            File f = new File("G:\\base\\a.txt");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.setLength(1024);
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_WRITE, 0, 1024);
            channel.close();
            raf.close();
            // 加上这几行代码,手动unmap
            Method m = FileChannelImpl.class.getDeclaredMethod("unmap",
                    MappedByteBuffer.class);
            m.setAccessible(true);
            m.invoke(FileChannelImpl.class, buffer);
            if (f.delete()){
                System.out.println("Temporary file deleted: " + f);
            }
            else {
                System.err.println("Not yet deleted: " + f);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
