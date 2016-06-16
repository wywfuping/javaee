package com.fuping.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTestCase {

    @Test
    public void testLog(){
        Logger logger= LoggerFactory.getLogger(LogTestCase.class);
        String name="宋小宝";
        String book="《二人转》";
        logger.trace("trace");
        logger.debug("debug");
        logger.info("{}借阅了{}",name,book);
        logger.warn("warn");
        logger.error("error");
    }

}
