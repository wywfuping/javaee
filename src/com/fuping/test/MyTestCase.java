package com.fuping.test;

import org.junit.Test;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

public class MyTestCase {

    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        System.out.println(token);
    }

    @Test
    public void testCaptcha() throws Exception {

        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMinLength(4);
        factory.setMaxLength(4);
        factory.setCharacters("01234567989");
        service.setWordFactory(factory);

        FileOutputStream outputStream = new FileOutputStream("f:/captcha.png");
        EncoderHelper.getChallangeAndWriteImage(service, "png", outputStream);

        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void testSubstring() {
        String header = "form-data; name=\"doc\"; filename=\"github-roam.pdf\"";
        header = header.substring(header.indexOf("filename=\""));
        header = header.substring(header.indexOf("\"")+1,header.length()-1);
        System.out.println(header);
    }
}
