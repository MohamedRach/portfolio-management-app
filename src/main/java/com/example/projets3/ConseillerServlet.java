package com.example.projets3;
import java.io.*;
import java.util.ArrayList;

import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
// ConseillerServlet.java
@WebServlet(name = "conseillerServlet", value = "/conseiller-servlet")
public class ConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ArrayList<ConseillerBean> conseillers = this.conseillerDao.getAllConseillers();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Liste des conseillers</h2>");
        out.println("<form action=\"/createConseiller.jsp\" method=\"get\">");
        out.println("<button class=\"btn btn-success float-right\" type=\"submit\">Créer</button></form>");

        out.println("<table border=\"1\">");
        out.println("<tr><th>Nom</th><th>Prénom</th><th>Email</th><th>Actions</th></tr>");

        for (ConseillerBean conseiller : conseillers) {
            out.println("<tr>");
            out.println("<td>" + conseiller.getNom() + "</td>");
            out.println("<td>" + conseiller.getPrenom() + "</td>");
            out.println("<td>" + conseiller.getEmail() + "</td>");

            // Ajouter les boutons "Modifier" et "Supprimer" avec des formulaires
            out.println("<td>");
            out.println("<form action=\"/modifier-conseiller\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"conseillerId\" value=\"" + conseiller.getId() + "\">");
            out.println("<button type=\"submit\">Modifier</button></form>");

            // Add a line break for better separation
            out.println("<br>");

            out.println("<form action=\"/supprimer-conseiller\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"conseillerId\" value=\"" + conseiller.getId() + "\">");
            out.println("<input type=\"submit\" value=\"Supprimer\"></form>");
            out.println("</td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}

