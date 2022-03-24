package com.yichen.casetest.test.bytebuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/23 17:54
 * @describe 研究 MappedByteBuffer
 *   =>  参考链接   https://www.cnblogs.com/swbzmx/p/5992592.html
 */
public class MappedByteBufferTest {

    public static void main(String[] args) {
        FileInputStream fis = null;
        File f = null;
        try {
//            f = File.createTempFile("Test", null);
            f = new File("G:\\base\\a.txt");
            fis = new FileInputStream(f);
            FileChannel fc = fis.getChannel();

            // 把文件映射到内存
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    (int) fc.size());


            fc.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Error! " + ex.getMessage());
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Error! " + e.getMessage());
            System.exit(3);
        }
        if (f != null){
            // 删除文件
            boolean deleted = f.delete();
            if (!(deleted)) {
                System.err.println("Could not delete file " + f.getName());
            }
            else {
                System.out.println("success delete file");
            }

            System.gc();

            // 删除文件
            deleted = f.delete();
            if (!(deleted)) {
                System.out.println("Could not delete file " + f.getName());
            }
            else {
                System.out.println("success delete file");
            }
        }

    }

}
