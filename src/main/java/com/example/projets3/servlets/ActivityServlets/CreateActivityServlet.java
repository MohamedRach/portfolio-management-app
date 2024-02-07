package com.example.projets3.servlets.ActivityServlets;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.projets3.bean.ActivityBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ActivityDao;
import com.example.projets3.dao.ConseillerDao.ActivityDaoImpl;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@MultipartConfig
 public class CreateActivityServlet extends HttpServlet {
    private ActivityDao activityDao;
    public void init() {

        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.activityDao = new ActivityDaoImpl(daoFactory);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new conseiller
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createActivity.jsp");
        dispatcher.forward(request, response);
        HttpSession session = request.getSession();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract conseiller data from the form
        Integer id_user = Integer.valueOf(request.getParameter("id_user"));
        String desc_buy = request.getParameter("desc_buy");
        String desc_sell = request.getParameter("desc_sell");

        HttpSession session = request.getSession();
        Integer id_conseiller = (Integer) session.getAttribute("id_conseiller");


        // Create a new ConseillerBean with the extracted data
        ActivityBean newActivity = new ActivityBean();
        newActivity.setId_user(id_user);
        newActivity.setDesc_buy(desc_buy);
        newActivity.setDesc_sell(desc_sell);
        newActivity.setId_conseiller(id_conseiller);




        try {
            // Add the new conseiller to the database
            activityDao.create(newActivity);

            // Redirect to the /conseillers page after successful creation
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } catch (DAOException e) {
            // Handle errors (you might want to display an error message or log the exception)
            e.printStackTrace();
            response.getWriter().println("Error creating activity");
        }
    }
}
