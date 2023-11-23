package com.example.projets3.servlets.UtilisateurServlets;

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
    private UserDao userDao;

    public void init() {
        daoFactory dao_Factory = daoFactory.getInstance();
        this.userDao = new UserDaoImpl(dao_Factory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ArrayList<UserBean> users = this.userDao.getAllUsers();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Liste des utilisateurs</h2>");
        out.println("<form action=\"/createUtilisateur.jsp\" method=\"get\">");
        out.println("<button class=\"btn btn-success float-right\" type=\"submit\">Créer</button></form>");

        out.println("<table border=\"1\">");
        out.println("<tr><th>Nom</th><th>Prénom</th><th>Email</th><th>Actions</th></tr>");

        for (UserBean user : users) {
            out.println("<tr>");
            out.println("<td>" + user.getNom() + "</td>");
            out.println("<td>" + user.getPrenom() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");

            // Ajouter les boutons "Modifier" et "Supprimer" avec des formulaires
            out.println("<td>");
            out.println("<form action=\"/modifier-utilisateur\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"userId\" value=\"" + user.getId() + "\">");
            out.println("<button type=\"submit\">Modifier</button></form>");

            // Add a line break for better separation
            out.println("<br>");

            out.println("<form action=\"/supprimer-utilisateur\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"userId\" value=\"" + user.getId() + "\">");
            out.println("<input type=\"submit\" value=\"Supprimer\"></form>");
            out.println("</td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
