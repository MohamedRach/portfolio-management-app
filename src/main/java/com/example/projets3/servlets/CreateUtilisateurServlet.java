package com.example.projets3.servlets;
import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.example.projets3.bean.UserBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.UserDao.UserDao;
import com.example.projets3.dao.UserDao.UserDaoImpl;
import com.example.projets3.dao.daoFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
@WebServlet(name = "createUtilisateurServlet", value = "/create-utilisateur")
public class CreateUtilisateurServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new user
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createUtilisateur.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract user data from the form
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        // Create a new UserBean with the extracted data
        UserBean newUser = new UserBean();
        newUser.setNom(nom);
        newUser.setPrenom(prenom);
        newUser.setEmail(email);

        try {
            // Add the new user to the database
            userDao.create(newUser);

            // Redirect to the /users page after successful creation
            response.sendRedirect(request.getContextPath() + "/users");
        } catch (DAOException e) {
            // Handle errors (you might want to display an error message or log the exception)
            e.printStackTrace();
            response.getWriter().println("Error creating user");
        }
    }
}
