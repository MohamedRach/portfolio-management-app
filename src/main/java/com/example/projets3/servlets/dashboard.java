package com.example.projets3.servlets;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.portfolioBean;
import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.hiringDao.hiringDaoImpl;
import com.example.projets3.dao.portfolio.portfolioDaoImpl;
import com.example.projets3.dao.portfolio.portfolioDao;
import com.example.projets3.servlets.portfolio.apiCommunication;
import com.example.projets3.dao.stock.stockDaoImpl;
import com.example.projets3.dao.stock.stockDao;
import com.example.projets3.dao.hiringDao.hiringDao;
import com.example.projets3.dao.hiringDao.hiringDaoImpl;
import com.example.projets3.servlets.portfolio.apiCommunication;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


@WebServlet(name = "dashboard", value = "/dashboard")
public class dashboard extends HttpServlet {
    private stockDao stockDao;
    private portfolioDao portfolioDao;
    private apiCommunication apiCommunication;
    private hiringDao hiringDao;
    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.stockDao = new stockDaoImpl(daoFactory);
        this.apiCommunication = new apiCommunication();
        this.portfolioDao = new portfolioDaoImpl(daoFactory);
        this.hiringDao = new hiringDaoImpl(daoFactory);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(Objects.equals(session.getAttribute("role"), "conseiller")){
            int conseiller_id = (int) session.getAttribute("id_conseiller");
            ArrayList<HiringBean> hirings = this.hiringDao.getAllClients(conseiller_id);
            ArrayList<portfolioBean> portfolios = new ArrayList<>();
            ArrayList<ArrayList<Object>> portfolioData = new ArrayList<ArrayList<Object>>();
            float total_stocks = 0;
            float total_value = 0;
            int total_portfolios = 0;
            ArrayList<String> stockName = new ArrayList<>();
            ArrayList<Float> percentage = new ArrayList<>();
            ArrayList<ArrayList<Object>> quoteData = new ArrayList<>();
            for (HiringBean hiring: hirings) {
                portfolios = this.portfolioDao.getPortfolios(hiring.getId_user());
                total_portfolios += portfolios.size();
                ArrayList<stockBean> stockNames = new ArrayList<>();

                if (portfolios.size() != 0) {
                    for (portfolioBean portfolio : portfolios) {
                        ArrayList<stockBean> stocks = this.stockDao.getStocks(portfolio.getId());

                        ArrayList<Object> ports = new ArrayList<>();
                        for (stockBean stock : stocks) {
                            //String symbole = this.apiCommunication.getStockSymbole(stock.getName());
                            ArrayList<Float> quote = this.apiCommunication.getQuoteData(stock.getName());
                            ArrayList<Object> gainData = new ArrayList<>();
                            gainData.add(stock.getName());
                            gainData.add(quote.get(1));
                            quoteData.add(gainData);
                            total_value += quote.get(0) * stock.getQuantity();
                            stockNames.add(stock);
                            total_stocks += stock.getQuantity();
                        }
                        ports.add(portfolio.getId());
                        ports.add(portfolio.getName());
                        portfolioData.add(ports);

                    }


                    for (stockBean stocks : stockNames) {
                        stockName.add(stocks.getName());
                        percentage.add(((float) stocks.getQuantity() / total_stocks) * 100);
                    }

                }
            }
            request.setAttribute("graphData1", stockName);
            request.setAttribute("graphData2", percentage);
            request.setAttribute("total_value", total_value);
            request.setAttribute("tableData", quoteData);
            request.setAttribute("total_portfolios", total_portfolios);
            request.setAttribute("total_stocks", total_stocks);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } else {
            int user_id = (int) session.getAttribute("id_user");
            ArrayList<portfolioBean> portfolios = this.portfolioDao.getPortfolios(user_id);
            float total_stocks = 0;
            float total_value = 0;
            ArrayList<stockBean> stockNames = new ArrayList<>();
            ArrayList<ArrayList<Object>> quoteData = new ArrayList<>();
            ArrayList<ArrayList<Object>> portfolioData = new ArrayList<ArrayList<Object>>();
            ArrayList<String> stockName = new ArrayList<>();
            ArrayList<Float> percentage = new ArrayList<>();
            if (portfolios.size() != 0) {
                for (portfolioBean portfolio : portfolios) {
                    ArrayList<stockBean> stocks = this.stockDao.getStocks(portfolio.getId());

                    ArrayList<Object> ports = new ArrayList<>();
                    for (stockBean stock : stocks) {
                        //String symbole = this.apiCommunication.getStockSymbole(stock.getName());
                        ArrayList<Float> quote = this.apiCommunication.getQuoteData(stock.getName());
                        ArrayList<Object> gainData = new ArrayList<>();
                        gainData.add(stock.getName());
                        gainData.add(quote.get(1));
                        quoteData.add(gainData);
                        total_value += quote.get(0) * stock.getQuantity();
                        stockNames.add(stock);
                        total_stocks += stock.getQuantity();
                    }
                    ports.add(portfolio.getId());
                    ports.add(portfolio.getName());
                    portfolioData.add(ports);

                }


                for (stockBean stocks : stockNames) {
                    stockName.add(stocks.getName());
                    percentage.add(((float) stocks.getQuantity() / total_stocks) * 100);
                }

            }
            request.setAttribute("total_value", total_value);
            request.setAttribute("tableData", quoteData);
            request.setAttribute("graphData1", stockName);
            request.setAttribute("graphData2", percentage);
            request.setAttribute("total_portfolios", portfolios.size());
            request.setAttribute("total_stocks", total_stocks);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}