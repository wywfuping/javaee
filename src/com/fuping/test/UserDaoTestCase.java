package com.fuping.test;


import com.fuping.dao.UserDao;
import com.fuping.entity.User;
import org.junit.Test;

public class UserDaoTestCase {

    private UserDao userDao=new UserDao();

    @Test
    public void testSave(){
        User user= new User("小王","焦作","456");
        userDao.save(user);
    }

    @Test
    public void testDel(){
        userDao.del(13);
    }

    @Test
    public void testQueryById(){
        userDao.queryById(1);
    }

    @Test
    public void testQueryAll(){
        userDao.queryAll();
    }
}
