package com.fuping.test;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class CodecTestCase {

    @Test
    public void testMd5() {
        String pwd = "123";
        String salt = "&*&^&^)(&(%%$^&(%U)&(&^)";
        String md5 = DigestUtils.md5Hex(pwd + salt);
        System.out.println(md5);
    }

    @Test
    public void testSha() {
        String pwd = "123456";
        String salt = "&*&^&^)(&(%%$^&(%U)&(&^)";
        String sha = DigestUtils.sha1Hex(pwd + salt);
        System.out.println(sha);
    }

    @Test
    public void testCheckFile() throws IOException {
        //414c052378a7f26f7ad0dfd7070e8c08
        //fc36ffc4c48cee677b66cc58944c6016
        FileInputStream inputStream = new FileInputStream("F:/1.jpeg");
        String md5=DigestUtils.md5Hex(inputStream);
        System.out.println(md5);
    }

}
