package com.fuping.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(PayServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.产生token
        String token = UUID.randomUUID().toString();
        //2.放入session
        HttpSession session = req.getSession();
        session.setAttribute("token", token);
        //3.存入表单
        req.setAttribute("token", token);

        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从表单中获取token
        String token = req.getParameter("token");
        //2.从session中获取token
        HttpSession session = req.getSession();
        String sessionToken = (String) session.getAttribute("token");
        //3.比较两个token值是否相等：①如果相等，则将session中的token删除，跳转到结果页；②如果不相等，则跳转到失败页
        if (token != null && token.equals(sessionToken)) {//此处不能写成sessionToken.equals(token)
            //4.将session中的token删除
            session.removeAttribute("token");

            String money = req.getParameter("money");
            logger.info("已成功扣款{}元", money);

            //5.请求转发，跳转到结果页
            req.getRequestDispatcher("/WEB-INF/views/paysuc.jsp").forward(req, resp);
        } else {
            logger.warn("表单重复提交");
            req.getRequestDispatcher("/WEB-INF/views/payerror.jsp").forward(req, resp);
        }
        //resp.sendRedirect("/paysuc");//使用重定向解决表单重复提交
    }
}
