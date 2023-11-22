package com.example.projets3.servlets;


import java.io.IOException;

import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "createConseillerServlet", value = "/create-conseiller")
public class CreateConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new conseiller
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createConseiller.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract conseiller data from the form
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        // Create a new ConseillerBean with the extracted data
        ConseillerBean newConseiller = new ConseillerBean();
        newConseiller.setNom(nom);
        newConseiller.setPrenom(prenom);
        newConseiller.setEmail(email);

        try {
            // Add the new conseiller to the database
            conseillerDao.createConseiller(newConseiller);

            // Redirect to the /conseillers page after successful creation
            response.sendRedirect(request.getContextPath() + "/conseiller-servlet");
        } catch (DAOException e) {
            // Handle errors (you might want to display an error message or log the exception)
            e.printStackTrace();
            response.getWriter().println("Error creating conseiller");
        }
    }
}
