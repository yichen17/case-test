package com.yichen.casetest.test.jsch;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.Collection;
/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 15:11
 * @describe
 */
public class Test1 {
    public static void main(String[] args) throws JSchException, SftpException {
        Collection<File> files = FileUtils.listFiles(new File("/tmp/test"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        long start = System.currentTimeMillis();
        SftpUtil2 sftpUtil = new SftpUtil2("152.136.237.34", 22, "root", "5r:u{CK!MF*n3","UTF-8");
        for (File file : files) {
            sftpUtil.uploadFile(file.getPath(),"/tmp/aa");
        }
        System.out.println("上传完毕,耗时：" + (System.currentTimeMillis() - start));
        sftpUtil.disconnectAll();
    }
}
