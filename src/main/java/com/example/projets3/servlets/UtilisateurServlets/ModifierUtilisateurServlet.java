package com.example.projets3.servlets.UtilisateurServlets;

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
// ModifierUtilisateurServlet.java
@WebServlet(name = "modifierUtilisateurServlet", value = "/modifier-utilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }

    // ModifierUtilisateurServlet.java


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérer l'ID de l'utilisateur à modifier
        String userId = request.getParameter("userId");
        if (userId != null) {
            int userIdInt = Integer.parseInt(userId);

            // Récupérer l'utilisateur à partir de la base de données
            UserBean user = userDao.find(userIdInt);

            if (user != null) {
                // Afficher le formulaire de modification avec les données de l'utilisateur
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/modifierUtilisateur.jsp");
                dispatcher.forward(request, response);
            } else {
                // Gérer le cas où l'utilisateur n'est pas trouvé
                response.getWriter().println("Utilisateur non trouvé");
            }
        } else {
            // Gérer l'absence de paramètre userId
            // ... (redirection, affichage d'un message d'erreur, etc.)
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérer les données du formulaire
        int userId = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        // Créer un objet UserBean avec les données du formulaire
        UserBean userToUpdate = new UserBean();
        userToUpdate.setId(userId);
        userToUpdate.setNom(nom);
        userToUpdate.setPrenom(prenom);
        userToUpdate.setEmail(email);

        try {
            // Mettre à jour l'utilisateur dans la base de données
            userDao.update(userToUpdate);

            // Rediriger vers la page /users après la mise à jour réussie
            response.sendRedirect(request.getContextPath() + "/users");
        } catch (DAOException e) {
            // Gérer les erreurs liées à la mise à jour de l'utilisateur
            e.printStackTrace(); // À des fins de débogage, vous pouvez imprimer la trace de la pile
            response.getWriter().println("Erreur lors de la mise à jour de l'utilisateur");
        }
    }


    // ... (autres méthodes, si nécessaires)
}