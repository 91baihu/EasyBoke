package com.easyboke.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件发送工具类
 */
@Component
public class MailUtil {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送简单文本邮件
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送密码重置邮件
     */
    public void sendPasswordResetEmail(String to, String username, String resetLink) {
        String subject = "Easy博客 - 密码重置";
        String content = "您好，" + username + "，\n\n" +
                "您正在申请重置Easy博客账号的密码，请点击以下链接完成重置：\n\n" +
                resetLink + "\n\n" +
                "该链接有效期为24小时，过期失效。\n" +
                "如果您没有申请重置密码，请忽略此邮件。\n\n" +
                "—— Easy博客团队";
        sendMail(to, subject, content);
    }
}
