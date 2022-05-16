package com.yichen.casetest.service.Impl;

import com.jcraft.jsch.ChannelSftp;
import com.yichen.casetest.model.SftpPool;
import com.yichen.casetest.service.SftpFileSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author ChenYingxin
 * @date 2021/1/15
 * @description
 **/
@Slf4j
@Service
public class SftpFileSystemServiceImpl implements SftpFileSystemService {

    @Autowired
    private SftpPool sftpPool;


    @Override
    public File downloadFile(String targetPath) throws Exception {
        ChannelSftp sftp = sftpPool.borrowObject();
        OutputStream outputStream = null;
        File file = null;
        try {
            log.info("Download file sftp {}",sftp);
            file = new File(targetPath.substring(targetPath.lastIndexOf("/") + 1));
            outputStream = new FileOutputStream(file);
            sftp.get(targetPath, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}，sftp {}, outputStream {}, file {}", targetPath, sftp, outputStream, file, e);
            throw new Exception("下载文件失败");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            sftpPool.returnObject(sftp);
        }
    }


}
