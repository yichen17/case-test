package com.yichen.casetest.model;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.yichen.casetest.config.sftp.SftpProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Properties;

/**
 * @author ChenYingxin
 * @date 2021/1/22
 * @description
 **/
@Data
@Slf4j
public class SftpFactory extends BasePooledObjectFactory<ChannelSftp> {

    // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    private SftpProperties properties;

    public SftpFactory(SftpProperties properties) {
        log.info("sftp properties {}", JSON.toJSONString(properties));
        this.properties = properties;
    }

    @Override
    public ChannelSftp create() throws Exception {
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(properties.getUsername(), properties.getHost(), properties.getPort());
            sshSession.setPassword(properties.getPassword());
            Properties sshConfig = new Properties();
            sshConfig.put(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, "no");
            sshSession.setConfig(sshConfig);
            // 设置心跳时间，默认为0，此时不发送心跳
//            sshSession.setServerAliveInterval(300000);
            // 设置最大失败次数
//            sshSession.setServerAliveCountMax(3);
            sshSession.connect(properties.getSessionConnectTimeout());
            ChannelSftp channel = (ChannelSftp) sshSession.openChannel(properties.getProtocol());
            channel.connect(properties.getChannelConnectedTimeout());
            log.info("创建新的 channel sftp 连接");
            return channel;
        } catch (JSchException e) {
            throw new Exception("连接sfpt失败", e);
        }
    }

    @Override
    public boolean validateObject(PooledObject<ChannelSftp> p) {
        log.warn("validate-object {}",p);
        ChannelSftp channelSftp = p.getObject();
        boolean result = (!channelSftp.isClosed()) && (!channelSftp.isEOF());
        log.warn("channelSftp-{}-validate-{}",p,result);
        return result;
    }

    @Override
    public PooledObject<ChannelSftp> wrap(ChannelSftp channelSftp) {
        return new DefaultPooledObject<>(channelSftp);
    }

    /**
     * 销毁sftp连接
     *
     * @param p
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<ChannelSftp> p) throws Exception {
        log.info("关闭sftp 连接");
        if (p != null) {
            ChannelSftp sftp = p.getObject();
            try {
                Session session = sftp.getSession();
                if (sftp != null) {
                    if (sftp.isConnected()) {
                        sftp.disconnect();
                        log.info("SSH Channel disconnected.channel={}", sftp);
                    } else if (sftp.isClosed()) {
                        log.info("sftp is closed already");
                    }
                    if (null != session) {
                        session.disconnect();
                        log.info("SSH session disconnected.session={}", session);
                    }
                }
            } catch (JSchException e) {
                log.error("sftp关闭连接失败", e);
            }
        }
    }
}
