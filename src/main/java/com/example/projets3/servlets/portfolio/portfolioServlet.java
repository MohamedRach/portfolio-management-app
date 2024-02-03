package com.example.projets3.servlets.portfolio;

import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.stock.stockDao;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.stock.stockDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "portfolioServlet", value = "/portfolio")
public class portfolioServlet extends HttpServlet {
    private stockDao stockDao;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.stockDao = new stockDaoImpl(daoFactory);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int portfolio_id = Integer.parseInt(request.getParameter("id"));
        ArrayList<stockBean> stocks = this.stockDao.getStocks(portfolio_id);
        System.out.println(stocks);
        request.getRequestDispatcher("portfolioPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        //int user_id = (int) session.getAttribute("id");
        /*
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        portfolioBean portfolio = new portfolioBean();
        portfolio.setName(name);
        portfolio.setDescription(description);
        portfolio.setUser_id(1);
        this.portfolioDao.createPortfolio(portfolio);

        response.sendRedirect("/dashboard");

         */


    }
}