package com.example.projets3.servlets.ConseillerServlets;


import com.example.projets3.bean.ConseillerBean;
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

@WebServlet(name = "supprimerConseillerServlet", value = "/supprimer-conseiller")
public class SupprimerConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupérer l'ID du conseiller à supprimer
        String conseillerId = request.getParameter("conseillerId");
        if (conseillerId != null) {
            int conseillerIdInt = Integer.parseInt(conseillerId);

            // Supprimer le conseiller de la base de données
            try {
                conseillerDao.deleteConseiller(conseillerIdInt);
                // Rediriger vers la page principale après la suppression
                response.sendRedirect(request.getContextPath() + "/conseillerList");
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

