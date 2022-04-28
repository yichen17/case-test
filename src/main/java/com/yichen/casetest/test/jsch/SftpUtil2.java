package com.yichen.casetest.test.jsch;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 15:10
 * @describe
 */
@Slf4j
public class SftpUtil2 {
    Session session;
    ChannelSftp channel;

    public String username;
    public String password;
    public String remoteHost;
    public Integer remotePort;
    public String charset;

    public SftpUtil2(String remoteHost, Integer remotePort, String username, String password, String charset) throws JSchException, SftpException {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.username = username;
        this.password = password;
        this.charset = charset;
        connect();
    }

    public void uploadFile(ChannelSftp channel, String src, String dest) throws SftpException {
        channel.put(src, dest);
    }

    public void uploadFile(String src, String dest) throws SftpException, JSchException {
        uploadFile(channel, src, dest);
    }

    public void connect() throws JSchException, SftpException {
        JSch jSch = new JSch();
        session = jSch.getSession(username, remoteHost, remotePort);
        session.setPassword(password);
        session.setConfig("PreferredAuthentications", "password");
        session.setConfig("StrictHostKeyChecking", "no");// 为session重新设置参数
        session.connect();
        channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect(5000);
        if (!"UTF-8".equalsIgnoreCase(charset)) {
            Class cl = ChannelSftp.class;
            Field f;
            try {
                f = cl.getDeclaredField("server_version");
                f.setAccessible(true);
                f.set(channel, 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        channel.setFilenameEncoding(charset);
    }

    public void disconnectAll() {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

}
