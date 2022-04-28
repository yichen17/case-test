package com.yichen.casetest.service;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author ChenYingxin
 * @date 2021/1/15
 * @description
 **/
@Service
@Slf4j
public class IMailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    private final String SUBJECT_LAST = "test";

    /**
     * 配置文件中我的qq邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送带附件的邮件
     *
     * @param to      收件人
     * @param accessory    附件
     * @param fileName    文件名
     * @param userName 用户实名
     */
    public void sendAttachmentsMail(String to, File accessory,String fileName,String userName) {
        // 主题
        String subject= userName + SUBJECT_LAST;
        // 内容
        String content= getContent();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            if (mailSender instanceof JavaMailSenderImpl){
                JavaMailSenderImpl mailSender = (JavaMailSenderImpl) this.mailSender;
                log.info("当前配置 {} {} {} {}",mailSender.getHost(),mailSender.getPort(),mailSender.getUsername(),mailSender.getPassword());
            }
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(accessory);
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }


    }


    public String getContent(){
        StringBuilder sb = new StringBuilder("<p style=\"font-family:Verdana, Arial, &quot;font-size:13px;\">");
        sb.append("<span style=\"font-family:Microsoft YaHei;\">您好：&nbsp; &nbsp; &nbsp; &nbsp;</span><br>");
        sb.append("<span style=\"font-family:Microsoft YaHei;\"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span><br>");
        sb.append("<span style=\"font-family:Microsoft YaHei;\"> &nbsp; &nbsp; 附件为您申请的结清证明，如有疑问可随时登录玖富万卡APP联系在线客服进行咨询</span><br>");
        sb.append("</p>");
        return sb.toString();
    }
}
