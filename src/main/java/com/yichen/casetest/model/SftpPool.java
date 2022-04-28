package com.yichen.casetest.model;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * @author ChenYingxin
 * @date 2021/1/22
 * @description
 **/
@Data
@Slf4j
public class SftpPool {

    private GenericObjectPool<ChannelSftp> pool;

    public SftpPool(SftpFactory factory) {
        this.pool = new GenericObjectPool<>(factory, factory.getProperties().getPool());
    }

    /**
     * 获取一个sftp连接对象
     *
     * @return sftp连接对象
     */
    public ChannelSftp borrowObject() throws Exception {
        try {
            log.info("borrowObject {}",printInformation());
            return pool.borrowObject();
        } catch (Exception e) {
            log.warn("获取对象出错 {}",e.getMessage());
            throw new Exception("SFTP连接池获取ftp连接失败", e);
        }
    }

    /**
     * 归还一个sftp连接对象
     *
     * @param channelSftp sftp连接对象
     */
    public void returnObject(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            pool.returnObject(channelSftp);
        }
        else {
            log.warn("归还空对象");
        }
        log.info("returnObject {}",printInformation());
    }

    public String printInformation(){
        return "当前使用数 => " + pool.getNumActive() + "当前空闲数 => " + pool.getNumIdle();
    }

}
