package com.fuping.test;

import org.apache.commons.mail.*;
import org.junit.Test;

public class EmailTestCase {
    @Test
    public void testSendEmail(){
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("******","******");
        email.setSmtpPort(25);
        email.setCharset("utf-8");

        try {
            email.setFrom("******@163.com");
            email.setSubject("这是一封邮件");
            email.setMsg("这是正文");
            email.addTo("******@qq.com");
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException("发送邮件异常",e);
        }
    }

    @Test
    public void testSendHtmlEmail(){
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("******","******");
        email.setSmtpPort(25);
        email.setCharset("utf-8");

        try {
            email.setFrom("******@163.com");
            email.setSubject("今天下午拍的照片");
            email.setHtmlMsg("<div style='color:red'>内容见附件</div><img src='http://ww1.sinaimg.cn/mw690/6b6e567cgw1f4uss5hzoaj20dv09o0u8.jpg'>");
            email.addTo("******@qq.com");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
            throw new RuntimeException("发送邮件异常",e);
        }
    }

    @Test
    public void testSendAttEmail(){
        EmailAttachment ea = new EmailAttachment();
        ea.setPath("G:/图片/桌面壁纸/14.jpg");
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("******","******");
        email.setSmtpPort(25);
        email.setCharset("utf-8");

        try {
            email.setFrom("******@163.com");
            email.setSubject("这是一封附件邮件");
            email.setMsg("这是正文");
            email.addTo("********@qq.com");
            email.attach(ea);
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException("发送邮件异常",e);
        }
    }

}
