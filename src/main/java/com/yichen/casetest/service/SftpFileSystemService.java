package com.yichen.casetest.service;

import java.io.File;

/**
 * sftp文件系统接口
 */
public interface SftpFileSystemService {
    /**
     * 文件下载
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    File downloadFile(String targetPath) throws Exception;
}
