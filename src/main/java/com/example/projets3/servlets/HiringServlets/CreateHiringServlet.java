package com.example.projets3.servlets.HiringServlets;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.projets3.bean.ActivityBean;
import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ActivityDao;
import com.example.projets3.dao.ConseillerDao.ActivityDaoImpl;
import com.example.projets3.dao.UserDao.HiringDao;
import com.example.projets3.dao.UserDao.HiringDaoImpl;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

public class CreateHiringServlet extends HttpServlet {
    private HiringDao hiringDao;
    private ConseillerDao conseillerDao;

    public void init() {

        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.hiringDao = new HiringDaoImpl(daoFactory);
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new conseiller

       // HttpSession session = request.getSession();
        //ArrayList<ConseillerBean> conseillers = this.conseillerDao.getAllConseillers();
       // request.setAttribute("conseillers", conseillers);
       // RequestDispatcher dispatcher = request.getRequestDispatcher("/createHiring.jsp");
       // dispatcher.forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract conseiller data from the form

        Integer id_conseiller = Integer.valueOf(request.getParameter("id_conseiller"));
        HttpSession session = request.getSession();
        Integer id_user = (Integer) session.getAttribute("id_user");
        // Create a new ConseillerBean with the extracted data
        HiringBean newHiring = new HiringBean();
        newHiring.setId_user(id_user);
        newHiring.setId_conseiller(id_conseiller);
        try {
            // Add the new conseiller to the database
            hiringDao.create(newHiring);

            // Redirect to the /conseillers page after successful creation
            response.sendRedirect(request.getContextPath() + "/conseiller");
        } catch (DAOException e) {
            // Handle errors (you might want to display an error message or log the exception)
            e.printStackTrace();
            response.getWriter().println("Error  hiring ");
        }
    }
}
