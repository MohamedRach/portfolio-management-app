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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

// SupprimerUtilisateurServlet.java
@WebServlet(name = "supprimerUtilisateurServlet", value = "/supprimer-utilisateur")
public class SupprimerUtilisateurServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupérer l'ID de l'utilisateur à supprimer
        String userId = request.getParameter("userId");
        if (userId != null) {
            int userIdInt = Integer.parseInt(userId);

            // Supprimer l'utilisateur de la base de données
            try {
                userDao.delete(userIdInt);
                // Rediriger vers la page principale après la suppression
                response.sendRedirect(request.getContextPath() + "/hello-servlet");
            } catch (DAOException e) {
                // Gérer les erreurs
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/erreur.jsp");
            }
        } else {
            // Gérer l'absence de paramètre userId
            // ... (redirection, affichage d'un message d'erreur, etc.)
        }
    }

    // ... (autres méthodes, si nécessaires)
}
