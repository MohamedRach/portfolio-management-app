package com.example.projets3.servlets;

import com.example.projets3.bean.CommentBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.ConseillerDao.CommentDao;
import com.example.projets3.dao.ConseillerDao.CommentDaoImpl;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
import com.example.projets3.dao.daoFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class getConseiller extends HttpServlet {
    private ConseillerDao dao;
    private CommentDao commentDao;
    private UserDao userDao;
    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.dao = new ConseillerDaoImpl(dao_Factory);
        this.commentDao = new CommentDaoImpl(dao_Factory);
        this.userDao = new UserDaoImpl(dao_Factory);


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(id == null) {
            ArrayList<ConseillerBean> conseillers = dao.getAllConseillers();
            request.setAttribute("conseillers", conseillers);
            request.getRequestDispatcher("user_conseiller.jsp").forward(request, response);
        } else {
            // Logique pour récupérer un conseiller spécifique en fonction de l'ID
            ConseillerBean conseiller = dao.findConseiller(Integer.parseInt(id));
            request.setAttribute("conseiller", conseiller);
            System.out.println("voila le result " +conseiller.getPostive_Reviews());
            ArrayList<CommentBean> comments = commentDao.comments(Integer.parseInt(request.getParameter("id")));
            ArrayList<UserBean> users = new ArrayList<>();
            for (CommentBean comment : comments) {
                ArrayList<Object> usersComments = new ArrayList<>();
                UserBean user = userDao.find(comment.getUser_id());
                users.add(user);
            }


            request.setAttribute("comments", comments);
            request.setAttribute("users", users);
            request.getRequestDispatcher("conseiller.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}