package com.example.projets3.servlets;



import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "modifierConseillerServlet", value = "/modifier-conseiller")
public class ModifierConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérer l'ID du conseiller à modifier
        String conseillerId = request.getParameter("conseillerId");
        if (conseillerId != null) {
            int conseillerIdInt = Integer.parseInt(conseillerId);

            // Récupérer le conseiller à partir de la base de données
            ConseillerBean conseiller = conseillerDao.findConseiller(conseillerIdInt);

            if (conseiller != null) {
                // Afficher le formulaire de modification avec les données du conseiller
                request.setAttribute("conseiller", conseiller);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/modifierConseiller.jsp");
                dispatcher.forward(request, response);
            } else {
                // Gérer le cas où le conseiller n'est pas trouvé
                response.getWriter().println("Conseiller non trouvé");
            }
        } else {
            // Gérer l'absence de paramètre conseillerId
            // ... (redirection, affichage d'un message d'erreur, etc.)
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérer les données du formulaire
        int conseillerId = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        // Créer un objet ConseillerBean avec les données du formulaire
        ConseillerBean conseillerToUpdate = new ConseillerBean();
        conseillerToUpdate.setId(conseillerId);
        conseillerToUpdate.setNom(nom);
        conseillerToUpdate.setPrenom(prenom);
        conseillerToUpdate.setEmail(email);

        try {
            // Mettre à jour le conseiller dans la base de données
            conseillerDao.updateConseiller(conseillerToUpdate);

            // Rediriger vers la page /conseillers après la mise à jour réussie
            response.sendRedirect(request.getContextPath() + "/conseiller-servlet");
        } catch (DAOException e) {
            // Gérer les erreurs liées à la mise à jour du conseiller
            e.printStackTrace(); // À des fins de débogage, vous pouvez imprimer la trace de la pile
            response.getWriter().println("Erreur lors de la mise à jour du conseiller");
        }
    }

    // ... (autres méthodes, si nécessaires)
}

