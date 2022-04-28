package com.yichen.casetest.controller;

import cn.hutool.extra.mail.MailUtil;
import com.yichen.casetest.service.IMailService;
import com.yichen.casetest.service.SftpFileSystemService;
import com.yichen.casetest.test.jsch.ConcurrentRequestTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/28 9:50
 * @describe sftp controller
 */
@RestController
@RequestMapping("/sftp")
@Slf4j
public class SftpController {

    @Autowired
    private SftpFileSystemService service;

    @Autowired
    private IMailService iMailService;

    @GetMapping("/download")
    public String download(){
        String filePath = "/test/pdf/register.pdf";
        FileOutputStream outputStream;
        FileInputStream inputStream;
        try {
            File file = service.downloadFile(filePath);
//            outputStream = new FileOutputStream("F:\\data\\pdf-test\\" + System.currentTimeMillis() + "-" + file.getName());
//            inputStream = new FileInputStream(file);
//            byte[] buffer = new byte[1024];
//            while(inputStream.read(buffer) != -1){
//                outputStream.write(buffer);
//            }
//            outputStream.flush();
//            inputStream.close();
//            outputStream.close();

            // 发邮件
//            iMailService.sendAttachmentsMail("q07218396@163.com", file, "test.pdf", "yichen");
            MailUtil.send("q07218396@163.com", "测试数据","测试jsch pipe close",false,file);

            return "成功";
        }
        catch (Exception e){
            log.error("下载文件出错 {}",e.getMessage(),e);
        }
        return "失败";
    }

//    @GetMapping("/open")
//    public String open(){
//        ConcurrentRequestTest.flag = true;
//        return "ok";
//    }

}
