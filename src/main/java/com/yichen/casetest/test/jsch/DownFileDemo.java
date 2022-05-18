package com.yichen.casetest.test.jsch;

import cn.hutool.extra.mail.MailUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/17 13:50
 * @describe 直接代码运行
 */
public class DownFileDemo {

    public static void main(String[] args) {
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession("root", "152.136.237.34", 22);
            sshSession.setPassword("5r:u{CK!MF*n3");
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect(15000);
            ChannelSftp channel = (ChannelSftp) sshSession.openChannel("sftp");
            channel.connect(15000);
            File file = new File("download.pdf");
            OutputStream outputStream = new FileOutputStream(file);
            channel.get("/test/pdf/register.pdf", outputStream);
            MailUtil.send("q07218396@163.com", "测试数据","测试jsch pipe close",false,file);
            outputStream.close();
            channel.disconnect();
            sshSession.disconnect();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
