package com.romaneekang.web.servlet;

import com.romaneekang.web.mapper.UserMapper;
import com.romaneekang.web.pojo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.mybatis数据库查询
        //2.1 获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.2获取session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //2.4 调用方法
        User user = userMapper.selectAllByUsernameUser(username);


        //业务逻辑
        //如果user为null，数据库中没有，登录失败
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (user != null) {
            writer.write("用户名已经存在，请返回登录！！！");
        } else {
            int i = userMapper.insertToUserTable(new User(0,username,password));
            //提交事务
            sqlSession.commit();
            //2.5 释放资源
            sqlSession.close();
            if (i>0) {
                writer.write("添加成功");
            } else {
                writer.write("添加失败");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
