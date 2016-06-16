package com.fuping.test;

import com.fuping.Service.UserService;
import com.fuping.entity.User;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTestCase {
    private UserService userService = new UserService();

    @Test
    public void testLogin(){
        User user=userService.login("Rose","123");
        Assert.assertNotNull(user);
    }


}
