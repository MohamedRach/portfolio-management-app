package com.example.projets3.servlets.authentification;

import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.servlets.authentification.authentification;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
import com.example.projets3.dao.daoFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

public class signupServlet extends HttpServlet {
    private UserDao userDao;
    private authentification auth;
    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
        this.auth = new authentification();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new user
        RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract user data from the form
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String pass = this.auth.hash(password.toCharArray());
        UserBean user = userDao.findByEmail(email);
        if (user == null) {
            try {

                UserBean newUser = new UserBean();
                newUser.setNom(nom);
                newUser.setPrenom(prenom);
                newUser.setEmail(email);
                newUser.setPassword(pass);
                userDao.create(newUser);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                // Redirect to the /users page after successful creation
                response.sendRedirect(request.getContextPath() + "/users");


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }




    }




}
