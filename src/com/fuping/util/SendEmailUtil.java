package com.fuping.util;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailUtil {
    public static Logger logger = LoggerFactory.getLogger(SendEmailUtil.class);
    /**
     * 发送html邮件
     * @param address 收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public static void sendHtmlEmail(String address,String subject,String content){
        HtmlEmail htmlEmail= new HtmlEmail();
        htmlEmail.setHostName(Config.get("email.hostname"));
        htmlEmail.setAuthentication(Config.get("email.username"),Config.get("email.password"));
        htmlEmail.setSmtpPort(new Integer(Config.get("email.smtpport","25")));
        htmlEmail.setCharset(Config.get("email.charset"));

        try {
            htmlEmail.setFrom(Config.get("email.fromEmail"));
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(content);
            htmlEmail.addTo(address);
            htmlEmail.send();

            logger.debug("给{}发送邮件成功！",address);
        } catch (EmailException e) {
            logger.debug("给{}发送邮件失败！",address);
            throw new RuntimeException("发送html邮件异常",e);
        }
    }
}
