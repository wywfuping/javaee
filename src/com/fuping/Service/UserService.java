package com.fuping.Service;


import com.fuping.dao.UserDao;
import com.fuping.entity.User;
import com.fuping.util.SendEmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private final String SALT = "&*&^&^)(&(%%$^&(%U)&(&^)";
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao = new UserDao();

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果登录成功，返回user对象；如果失败，则返回null。
     */
    public User login(final String username, String password) {
        final User user = userDao.findUserByName(username);
        password = DigestUtils.md5Hex(password + SALT);
        logger.debug(user.getUsername(),user.getPassword());
        if (user != null && user.getPassword().equals(password)) {
            logger.debug("{}登录成功", username);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SendEmailUtil.sendHtmlEmail(user.getAddress(), "登录信息提示", "账号" + username + "登录成功！");
                }
            });
            thread.start();
            return user;
        }
        return null;
    }
}
