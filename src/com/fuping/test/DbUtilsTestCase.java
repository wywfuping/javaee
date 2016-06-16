package com.fuping.test;

import com.fuping.entity.User;
import com.fuping.util.ConnectionManger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbUtilsTestCase {
    @Test
    public void testInsert() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into t_user(username,address,password) values(?,?,?)";
        Connection connection = ConnectionManger.getConnection();
        try {
            queryRunner.update(connection, sql, "Lucy", "USA", "456");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testDelete() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from t_user where id=?";
        Connection connection = ConnectionManger.getConnection();
        try {
            queryRunner.update(connection, sql, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testUpdate() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update t_user set username=? where id=?";
        Connection connection = ConnectionManger.getConnection();
        try {
            queryRunner.update(connection, sql, "Rose", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testQueryById() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from t_user where id=?";
        Connection connection = ConnectionManger.getConnection();
        try {
            User user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), 1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testQueryAll() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from t_user";
        Connection connection = ConnectionManger.getConnection();
        try {
            List<User> userlist = queryRunner.query(connection, sql, new BeanListHandler<>(User.class));
            System.out.println(userlist);
            Assert.assertEquals(userlist.size(), 3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testQueryByIdToMap() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from t_user where id=?";
        Connection connection = ConnectionManger.getConnection();
        try {
            Map<String, Object> result = queryRunner.query(connection, sql, new MapHandler(), 1);
            System.out.println(result);
            Assert.assertNotNull(result);
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println(entry.getKey()+"->"+entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }
    @Test
    public void testQueryAllToMap() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from t_user";
        Connection connection = ConnectionManger.getConnection();
        try {
            List<Map<String,Object>> resultlist = queryRunner.query(connection, sql, new MapListHandler());
            Assert.assertEquals(resultlist.size(),3);
            for (int i=0;i<resultlist.size();i++){
                Map<String,Object> result = resultlist.get(i);
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                    System.out.println(entry.getKey()+"->"+entry.getValue());
                }
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testCount(){
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select count(*) from t_user";
        Connection connection = ConnectionManger.getConnection();
        try {
            Long count = queryRunner.query(connection,sql,new ScalarHandler<Long>());
            Assert.assertEquals(new Long(3),count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManger.closeConnection(connection);
        }
    }

    @Test
    public void testQueryUsername(){
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select username from t_user";
        Connection connection = ConnectionManger.getConnection();
        try {
            List<String> namelist = queryRunner.query(connection,sql,new ColumnListHandler<String>());
            Assert.assertEquals(3,namelist.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManger.closeConnection(connection);
        }
    }
}
