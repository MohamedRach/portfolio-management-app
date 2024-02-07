package com.example.projets3.servlets.portfolio;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.portfolioBean;
import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.hiringDao.hiringDaoImpl;
import com.example.projets3.dao.hiringDao.hiringDao;
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
import java.util.Objects;


@WebServlet(name = "portfolioServlet", value = "/portfolio")
public class portfolioServlet extends HttpServlet {
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
        if(request.getParameter("id") == null) {
            if(Objects.equals(session.getAttribute("role"), "user")){
                ArrayList<portfolioBean> portfolios = this.portfolioDao.getPortfolios((int) session.getAttribute("id_user"));
                request.setAttribute("portfolios", portfolios);
                request.getRequestDispatcher("portfolios.jsp").forward(request, response);
            } else if(Objects.equals(session.getAttribute("role"), "conseiller")){
                ArrayList<HiringBean> hirings = this.hiringDao.getAllClients((int) session.getAttribute("id_conseiller"));
                ArrayList<portfolioBean> portfolios = new ArrayList<>();
                for (HiringBean hiring: hirings){
                    ArrayList<portfolioBean> ports = this.portfolioDao.getPortfolios(hiring.getId_user());
                    for(portfolioBean port: ports){
                        portfolios.add(port);
                    }
                }
                request.setAttribute("portfolios", portfolios);
                request.getRequestDispatcher("portfolios.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }


        }
        int portfolio_id = Integer.parseInt(request.getParameter("id"));
        portfolioBean portfolio = this.portfolioDao.getPortfolio(portfolio_id);
        ArrayList<stockBean> stocks = this.stockDao.getStocks(portfolio_id);
        ArrayList<Float> priceData = new ArrayList<>();
        ArrayList<Float> quoteData = new ArrayList<>();
        ArrayList<ArrayList<String>> stockData = null;
        ArrayList<ArrayList<String>> financialData = new ArrayList<>();
        float total_value = 0;
        for(stockBean stock:stocks) {
            //String symbole = this.apiCommunication.getStockSymbole(stock.getName());
            ArrayList<String> subList = this.apiCommunication.getFinancials(stock.getName());
            subList.add(Integer.toString(stock.getId()));
            financialData.add(subList);
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
        request.setAttribute("user_id", portfolio.getUser_id());
        request.setAttribute("financialData", financialData);
        request.setAttribute("portfolio", portfolio.getName());
        request.setAttribute("value", total_value);
        request.setAttribute("priceData", priceData);
        request.setAttribute("stockData",stockData );
        request.getRequestDispatcher("portfolioPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("id_user");
        System.out.println(request.getParameter("delete"));
        if(Objects.equals(request.getParameter("delete"), "stock")){
            int stockId = Integer.parseInt(request.getParameter("stockId"));
            this.stockDao.deleteStock(stockId);
            response.sendRedirect("/portfolio");
        } else if (Objects.equals(request.getParameter("delete"), "portfolio")) {
            int portfolioId = Integer.parseInt(request.getParameter("porttfolioId"));
            this.portfolioDao.deletePortfolio(portfolioId);
            response.sendRedirect("/portfolio");
        } else {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int portfolio_id = Integer.parseInt(request.getParameter("portfolio"));
            float purshasePrice = Float.parseFloat(request.getParameter("purshasePrice"));
            String purshaseDate = request.getParameter("purshaseDate");
            String stockName = request.getParameter("stockName");
            stockBean stock = new stockBean();
            stock.setQuantity(quantity);
            stock.setPortfolio_id(portfolio_id);
            stock.setPurchasePrice(purshasePrice);
            stock.setPurchaseDate(purshaseDate);
            stock.setName(stockName);
            this.stockDao.addStock(stock);
            response.sendRedirect("/portfolio?id=" + portfolio_id);
        }
    }
}