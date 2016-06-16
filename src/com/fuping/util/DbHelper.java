package com.fuping.util;

import com.fuping.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


public class DbHelper {
    private static Logger logger= LoggerFactory.getLogger(DbHelper.class);

    public static void update(String sql,Object...params){
        QueryRunner queryRunner= new QueryRunner(ConnectionManger.getDataSource());
        try {
            queryRunner.update(sql,params);
            logger.debug("SQL:{}",sql);
        } catch (SQLException e) {http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1463574658346_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=%E5%AE%A0%E7%89%A9
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行"+sql+"异常",e);
        }
    }

    public static <T> T query(String sql, ResultSetHandler<T> handler,Object...params){
        QueryRunner queryRunner = new QueryRunner(ConnectionManger.getDataSource());
        try {
           T result= queryRunner.query(sql, handler,params);
            logger.debug("SQL:{}",sql);
            return result;
        } catch (SQLException e) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行"+sql+"异常",e);
        }
    }
}
