package com.fuping.web;

import com.fuping.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private Logger logger= LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String captcha = req.getParameter("captcha");
        String sessionCaptcha= (String) req.getSession().getAttribute("captcha");

        if(captcha!=null && captcha.equalsIgnoreCase(sessionCaptcha)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            UserService userService = new UserService();
            if(userService.login(username,password) != null){
                logger.debug("{}登录成功！",username);
            }else{
                logger.error("用户名或密码错误");
            }
           // resp.sendRedirect("/login?code=100");
        }else {
            logger.error("验证码错误！");
            resp.sendRedirect("/login?code=1001");
        }
    }
}
