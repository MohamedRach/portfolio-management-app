package com.example.projets3.servlets.portfolio;

import com.example.projets3.bean.portfolioBean;
import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.portfolio.portfolioDaoImpl;
import com.example.projets3.dao.portfolio.portfolioDao;
import com.example.projets3.dao.stock.stockDao;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.stock.stockDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.projets3.servlets.portfolio.apiCommunication;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "portfolioServlet", value = "/portfolio")
public class portfolioServlet extends HttpServlet {
    private stockDao stockDao;
    private portfolioDao portfolioDao;
    private apiCommunication apiCommunication;

    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.stockDao = new stockDaoImpl(daoFactory);
        this.apiCommunication = new apiCommunication();
        this.portfolioDao = new portfolioDaoImpl(daoFactory);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int portfolio_id = Integer.parseInt(request.getParameter("id"));
        portfolioBean portfolio = this.portfolioDao.getPortfolio(portfolio_id,1);
        ArrayList<stockBean> stocks = this.stockDao.getStocks(portfolio_id);
        ArrayList<Float> priceData = new ArrayList<>();
        ArrayList<Float> quoteData = new ArrayList<>();
        ArrayList<ArrayList<String>> stockData = null;
        ArrayList<ArrayList<String>> financialData = new ArrayList<>();
        float total_value = 0;
        for(stockBean stock:stocks) {
            ArrayList<String> subList = this.apiCommunication.getFinancials(stock.getName());
            financialData.add(subList);
            //String symbole = this.apiCommunication.getStockSymbole(stock.getName());
            stockData = this.apiCommunication.getStockData(stock.getName(), "TIME_SERIES_INTRADAY");
            ArrayList<Float> quote = this.apiCommunication.getQuoteData(stock.getName());
            for (int i = 0; i < stockData.size(); i++) {
                if(priceData.size() > i) {
                    priceData.set(i, priceData.get(i) + Float.parseFloat(stockData.get(i).get(1))*stock.getQuantity());
                } else {
                    priceData.add(Float.parseFloat(stockData.get(i).get(1))*stock.getQuantity());
                }
            }
            quoteData.add(quote.get(0)*stock.getQuantity());
        }
        for(Float price: quoteData){
            total_value += price;
        }
        request.setAttribute("financialData", financialData);
        request.setAttribute("portfolio", portfolio.getName());
        request.setAttribute("value", total_value);
        request.setAttribute("priceData", priceData);
        request.setAttribute("stockData",stockData );
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