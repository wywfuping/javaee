package com.fuping.dao;

import com.fuping.entity.User;
import com.fuping.util.DbHelper;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class UserDao {
    public void save(User user) {
        String sql = "insert into t_user(username,address,password) values(?,?,?)";
        DbHelper.update(sql, user.getUsername(), user.getAddress(), user.getPassword());
    }

    public void del(Integer id) {
        String sql = "delete from t_user where id=?";
        DbHelper.update(sql, id);
    }

    public void queryById(Integer id) {
        String sql = "select * from t_user where id=?";
        DbHelper.query(sql, new BeanHandler<>(User.class), id);
    }

    public User findUserByName(String username) {
        String sql = "select * from t_user where username=?";
        return DbHelper.query(sql, new BeanHandler<>(User.class), username);
    }

    public void queryAll() {
        String sql = "select * from t_user";
        DbHelper.query(sql, new BeanListHandler<>(User.class));
    }
}
