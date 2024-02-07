package com.example.projets3.servlets;

import com.example.projets3.bean.*;
import com.example.projets3.dao.ConseillerDao.*;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.UserDao.HiringDaoImpl;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
import com.example.projets3.dao.daoFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.projets3.dao.UserDao.HiringDao;
import com.example.projets3.dao.UserDao.HiringDaoImpl;
import com.example.projets3.bean.HiringBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class getConseiller extends HttpServlet {
    private ConseillerDao dao;
    private CommentDao commentDao;
    private UserDao userDao;
    private HiringDao hiringDao;
    private ActivityDao activityDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.dao = new ConseillerDaoImpl(dao_Factory);
        this.commentDao = new CommentDaoImpl(dao_Factory);
        this.userDao = new UserDaoImpl(dao_Factory);
        this.hiringDao = new HiringDaoImpl(dao_Factory);
        this.activityDao = new ActivityDaoImpl(dao_Factory);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Integer id_user = (Integer) session.getAttribute("id_user");
        System.out.println(id_user);
        // Récupérer les IDs des conseillers déjà embauchés par l'utilisateur connecté
        Set<Integer> hiredConseillersIds = hiringDao.getHiredConseillersIds(id_user);
        System.out.println(hiredConseillersIds);
        request.setAttribute("hiredConseillersIds",hiredConseillersIds);

        if(id == null) {
            // Récupérer l'ID de l'utilisateur connecté à partir de la session

            ArrayList<ConseillerBean> conseillers = dao.getAllConseillers();
            request.setAttribute("hiredConseillersIds",hiredConseillersIds);
            request.setAttribute("conseillers", conseillers);

            request.getRequestDispatcher("user_conseiller.jsp").forward(request, response);
        }
        else {

            ArrayList<ConseillerBean> conseillers = dao.getAllConseillers();

            if(hiredConseillersIds.contains(Integer.parseInt(id))){


                ConseillerBean conseiller = dao.findConseiller(Integer.parseInt(id));
                request.setAttribute("conseiller", conseiller);
                ArrayList<CommentBean> comments = commentDao.comments(Integer.parseInt(request.getParameter("id")));
                ArrayList<UserBean> users = new ArrayList<>();
                for (CommentBean comment : comments) {
                    ArrayList<Object> usersComments = new ArrayList<>();
                    UserBean user = userDao.find(comment.getUser_id());
                    users.add(user);
                }
                ArrayList<ActivityBean> activities = this.activityDao.getAllActivity();
                request.setAttribute("activities", activities);

                request.setAttribute("comments", comments);
                request.setAttribute("users", users);
                request.getRequestDispatcher("conseiller.jsp").forward(request, response);
            }



            else{
                // Logique pour récupérer un conseiller spécifique en fonction de l'ID
                ConseillerBean conseiller = dao.findConseiller(Integer.parseInt(id));
                request.setAttribute("conseiller", conseiller);
                ArrayList<CommentBean> comments = commentDao.comments(Integer.parseInt(request.getParameter("id")));
                ArrayList<UserBean> users = new ArrayList<>();
                for (CommentBean comment : comments) {
                    ArrayList<Object> usersComments = new ArrayList<>();
                    UserBean user = userDao.find(comment.getUser_id());
                    users.add(user);
                }
                ArrayList<ActivityBean> activities = this.activityDao.getAllActivity();
                request.setAttribute("activities", activities);
                request.setAttribute("comments", comments);
                request.setAttribute("users", users);
                request.getRequestDispatcher("conseiller.jsp").forward(request, response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract conseiller data from the form

        Integer id_conseiller = Integer.valueOf(request.getParameter("id_conseiller"));
        Integer id_btn = Integer.valueOf(request.getParameter("id_btn"));
        HttpSession session = request.getSession();
        Integer id_user = (Integer) session.getAttribute("id_user");
        Set<Integer> hiredConseillersIds = hiringDao.getHiredConseillersIds(id_user);
        // Retrieve conseiller data from the database
        ConseillerBean conseiller = dao.findConseiller(id_conseiller);
        // Check if the conseiller is hired
        if (hiredConseillersIds.contains(conseiller.getId())==true) {

            // Create a new CommentBean with the extracted data
            CommentBean comment = new CommentBean();
            String content = request.getParameter("comment");
            if(content==null){
                response.sendRedirect(request.getContextPath() + "/conseiller?id=" + id_conseiller);
            }
            else {
                comment.setComment(content);
                comment.setConseiller_id(id_conseiller);
                comment.setUser_id(id_user);

                // Add the new comment to the database
                try {
                    commentDao.create(comment);
                    response.sendRedirect(request.getContextPath() + "/conseiller?id=" + id_conseiller);
                } catch (DAOException e) {
                    // Handle errors (you might want to display an error message or log the exception)
                    e.printStackTrace();
                    response.getWriter().println("Error creating comment");
                    return; // Exit the method if an error occurs
                }
            }
        }

        else {
            if (id_btn == 1) {
                response.sendRedirect(request.getContextPath() + "/conseiller?id=" + id_conseiller);
            }
            else {
                // Create a new HiringBean
                HiringBean newHiring = new HiringBean();
                newHiring.setId_user(id_user);
                newHiring.setId_conseiller(id_conseiller);

                // Add the new hiring to the database
                try {
                    hiringDao.create(newHiring);
                } catch (DAOException e) {
                    // Handle errors (you might want to display an error message or log the exception)
                    e.printStackTrace();
                    response.getWriter().println("Error hiring");
                    return; // Exit the method if an error occurs
                }

                // Redirect to the /conseillers page after successful creation
                response.sendRedirect(request.getContextPath() + "/conseiller");
            }
        }
    }


}