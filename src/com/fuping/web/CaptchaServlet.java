package com.fuping.web;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha.png")
public class CaptchaServlet extends HttpServlet{
    private Logger logger= LoggerFactory.getLogger(CaptchaServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(25, 60,170)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        OutputStream outputStream = resp.getOutputStream();//响应输出流
        String captcha = EncoderHelper.getChallangeAndWriteImage(service,"png",outputStream);

        //将产生的验证码放入到session中
        HttpSession session = req.getSession();
        session.setAttribute("captcha",captcha);

        logger.debug("验证码：{}",captcha);
        outputStream.flush();
        outputStream.close();
    }

}
