package com.example.projets3.servlets.ConseillerServlets;
import java.io.*;
import java.util.ArrayList;

import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
// ConseillerServlet.java
@WebServlet(name = "conseillerServlet", value = "/conseillerList")
public class ConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ConseillerBean> conseillers = this.conseillerDao.getAllConseillers();
        request.setAttribute("conseillers", conseillers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/conseillers.jsp");
        dispatcher.forward(request, response);
    }
}