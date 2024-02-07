package com.example.projets3.servlets.portfolio;

import com.example.projets3.bean.portfolioBean;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.portfolio.portfolioDao;
import com.example.projets3.dao.portfolio.portfolioDaoImpl;
import com.example.projets3.dao.stock.stockDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "addPortfolio", value = "/addPortfolio")
public class addPortfolio extends HttpServlet {
    private portfolioDao portfolioDao;
    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.portfolioDao = new portfolioDaoImpl(daoFactory);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addPortfolio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("id");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        portfolioBean portfolio = new portfolioBean();
        portfolio.setName(name);
        portfolio.setDescription(description);
        portfolio.setUser_id(user_id);
        this.portfolioDao.createPortfolio(portfolio);

        response.sendRedirect("/dashboard");
    }
}