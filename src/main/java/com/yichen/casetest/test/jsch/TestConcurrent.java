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
 * @date 2022/5/12 16:16
 * @describe
 */
public class TestConcurrent {
    public static void main(String[] args) throws JSchException, SftpException {
        Collection<File> files = FileUtils.listFiles(new File("F:\\data\\upload"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        SftpUtil2 sftpUtil = new SftpUtil2("152.136.237.34", 22, "root", "5r:u{CK!MF*n3","UTF-8");
        files.parallelStream().forEach(file -> {
            try {
                sftpUtil.uploadFile(file.getPath(),"/test/upload");
            } catch (SftpException | JSchException e) {
                e.printStackTrace();
            }
        });

        sftpUtil.disconnectAll();
    }
}
