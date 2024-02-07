package com.example.projets3.servlets.authentification;

import com.example.projets3.bean.UserBean;

import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.servlets.authentification.googleAuth;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class loginServlet extends HttpServlet {
    private UserDao userDao;
    private ConseillerDao conseillerDao;
    private authentification auth;
    private googleAuth google_auth;
    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.google_auth = googleAuth.getInstance();
        this.auth = new authentification();
        this.userDao = new UserDaoImpl(dao_Factory);
        this.conseillerDao = new ConseillerDaoImpl(dao_Factory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new user
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract user data from the form
        String type = request.getParameter("type");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (type != null) {
            String url = google_auth.generateUrl();
            response.sendRedirect(request.getContextPath() + url);
        } else {
            try {
                // find the user in the database
                UserBean User = userDao.findByEmail(email);
                ConseillerBean Conseiller = conseillerDao.findByEmail(email);
                boolean auth_success = this.auth.authenticate(password.toCharArray(), User.getPassword());
                if(auth_success) {
                    HttpSession session = request.getSession();

                    session.setAttribute("email", email);

                    session.setAttribute("id_user", User.getId()); // set the user in the session
                    //session.setAttribute("id_conseiller",Conseiller.getId()); // set the user in the session


                    // Redirect to the /users page after successful creation
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                } else {
                    System.out.println("unauthorized");
                }

            } catch (DAOException e) {
                // Handle errors (you might want to display an error message or log the exception)
                e.printStackTrace();
                response.getWriter().println("Error creating user");
            }
        }






    }
}
