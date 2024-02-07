package com.example.projets3.servlets.ActivityServlets;
import com.example.projets3.bean.ActivityBean;
import com.example.projets3.dao.ConseillerDao.ActivityDao;
import com.example.projets3.dao.ConseillerDao.ActivityDaoImpl;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet(name = "supprimerActivityServlet", value = "/supprimer-activity")
public class SupprimerActivityServlet extends HttpServlet {

    private ActivityDao activityDao;
    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.activityDao = new ActivityDaoImpl(daoFactory);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupérer l'ID du conseiller à supprimer
        String activityId = request.getParameter("activityId");
        if (activityId != null) {
            int activityIdInt = Integer.parseInt(activityId);

            // Supprimer le conseiller de la base de données
            try {
                activityDao.deleteActivity(activityIdInt);
                // Rediriger vers la page principale après la suppression
                response.sendRedirect(request.getContextPath() + "/conseiller");
            } catch (DAOException e) {
                // Gérer les erreurs
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/erreur.jsp");
            }
        } else {
            // Gérer l'absence de paramètre conseillerId
            // ... (redirection, affichage d'un message d'erreur, etc.)
        }
    }

    // ... (autres méthodes, si nécessaires)
}



