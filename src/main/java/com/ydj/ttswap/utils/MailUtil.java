package com.ydj.ttswap.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailUtil {
    @Value("${spring.mail.username}")
    String from;

    @Autowired
    JavaMailSender mailSender;
    //简单邮件
    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //发件人
        message.setTo(to);//收件人
        message.setSubject(subject); //标题
        message.setText(content);   //文件内容

        System.out.println(message);
        try {
            mailSender.send(message);
            System.out.println("简单邮件发送成功！");
        } catch (Exception e){
            System.out.println("发送简单邮件时发生异常！"+e);
        }
    }
    //html格式邮件
    public void sendHtmlMail(String to, String subject, String content){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            System.out.println("html邮件发送成功!");
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常！"+e);
        }
    }

    //带附件的邮件
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            System.out.println("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            System.out.println("发送带附件的邮件时发生异常！" + e);
        }
    }

    //带静态资源的邮件
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            System.out.println("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            System.out.println("发送嵌入静态资源的邮件时发生异常！" + e);
        }
    }
}
