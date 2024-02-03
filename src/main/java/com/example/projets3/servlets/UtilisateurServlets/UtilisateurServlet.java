package com.example.projets3.servlets.UtilisateurServlets;

import java.io.*;
import java.util.ArrayList;

import com.example.projets3.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
@WebServlet(name = "UtilisateurServlet", value = "/users")
public class UtilisateurServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        /*if(session.getAttribute("email") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }*/
        ArrayList<UserBean> users = this.userDao.getAllUsers();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp");
        dispatcher.forward(request,response);
    }
}
