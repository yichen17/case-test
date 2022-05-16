package com.yichen.casetest.model;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.IO;
import com.yichen.casetest.utils.ReflectUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.io.PipedInputStream;

/**
 * @author ChenYingxin
 * @date 2021/1/22
 * @description
 **/
@Data
@Slf4j
public class SftpPool {

    @Autowired
    private SftpFactory sftpFactory;

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
//            ChannelSftp channelSftp;
//            while( (channelSftp = pool.borrowObject()) == null){
//                log.warn("取出的对象为null,从对象池中移除");
//                sftpFactory.destroyObject(sftpFactory.wrap(channelSftp));
                // 感觉没用
//                pool.invalidateObject(null, DestroyMode.ABANDONED);
//            }

            ChannelSftp channelSftp = pool.borrowObject();
            log.info("borrowObject {}",printInformation());
            printChannelSftpStatus(channelSftp);
            return channelSftp;
        } catch (Exception e) {
            log.warn("获取对象出错 {}",e.getMessage());
            throw new Exception("SFTP连接池获取ftp连接失败", e);
        }
    }

    private void printChannelSftpStatus(ChannelSftp channelSftp){
        try {
            IO io = ReflectUtils.getFieldFromObject(channelSftp, "io", IO.class);
            if (io != null){
                InputStream inputStream = ReflectUtils.getFieldFromObject(io, "in", InputStream.class);
                if (inputStream instanceof PipedInputStream){
                    boolean closedByWriter = ReflectUtils.getFieldFromObject(inputStream, "closedByWriter", Boolean.class);
                    boolean closedByReader = ReflectUtils.getFieldFromObject(inputStream, "closedByReader", Boolean.class);
                    log.warn("channelSftp {} 输入流 {} {}", channelSftp, closedByWriter, closedByReader);
                }
            }
        }
        catch (Exception e){
            log.error("出现错误 {}",e.getMessage(),e);
        }
    }

    /**
     * 归还一个sftp连接对象
     *
     * @param channelSftp sftp连接对象
     */
    public void returnObject(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            printChannelSftpStatus(channelSftp);
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
