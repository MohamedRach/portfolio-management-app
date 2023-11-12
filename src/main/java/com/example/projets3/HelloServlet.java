package com.example.projets3;

import java.io.*;
import java.util.ArrayList;

import com.example.projets3.bean.UserBean;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private daoFactory factory;
    private UserDao userDao;
    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        //PersonDAO person = new PersonDAO();
        ArrayList<UserBean> users = this.userDao.getAllUsers();
        // Hello

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        users.forEach(user -> {
            out.println("<h1>" + user.getNom() + "</h1>");
            out.println("<h1>" + user.getPrenom() + "</h1>");
            out.println("<h1>" + user.getEmail() + "</h1>");
        });


        out.println("</body></html>");
    }

    public void destroy() {
    }
}