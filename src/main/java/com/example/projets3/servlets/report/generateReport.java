package com.example.projets3.servlets.report;

import com.example.projets3.bean.HiringBean;
import com.example.projets3.bean.portfolioBean;
import com.example.projets3.bean.reportBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.hiringDao.hiringDao;
import com.example.projets3.dao.hiringDao.hiringDaoImpl;
import com.example.projets3.dao.portfolio.portfolioDaoImpl;
import com.example.projets3.dao.portfolio.portfolioDao;
import com.example.projets3.dao.reportDao.reportDao;
import com.example.projets3.dao.reportDao.reportDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


@WebServlet(name = "generateReport", value = "/generateReport")
public class generateReport extends HttpServlet {
    private reportDao reportDao;
    private portfolioDao portfolioDao;
    private hiringDao hiringDao;
    public void init(){
        daoFactory dao = com.example.projets3.dao.daoFactory.getInstance();
        this.reportDao = new reportDaoImpl(dao);
        this.portfolioDao = new portfolioDaoImpl(dao);
        this.hiringDao = new hiringDaoImpl(dao);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id_conseiller = (int) session.getAttribute("id_conseiller");
        ArrayList<HiringBean> hirings = this.hiringDao.getAllClients(id_conseiller);
        ArrayList<portfolioBean> ports = new ArrayList<>();
        for(HiringBean hr: hirings){
            ArrayList<portfolioBean> portfolios = this.portfolioDao.getPortfolios(hr.getId_user());
            for(portfolioBean pr: portfolios){
                ports.add(pr);
            }
        }
        request.setAttribute("portfolios", ports);
        request.getRequestDispatcher("generateReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String firstPeriodStart =  request.getParameter("firstPeriodStart");
        String firstPeriodEnd =   request.getParameter("firstPeriodEnd");
        String secondPeriodStart =   request.getParameter("secondPeriodStart");
        String secondPeriodEnd =   request.getParameter("secondPeriodEnd");
        int portfolio_id = Integer.parseInt(request.getParameter("portfolio"));
        String thirdPeriodStart =   request.getParameter("thirdPeriodStart");
        String thirdPeriodEnd =  request.getParameter("thirdPeriodEnd");

        reportBean report = new reportBean();
        report.setName(name);
        report.setFirst_period_start(firstPeriodStart);
        report.setFirst_period_end(firstPeriodEnd);
        report.setSecond_period_start(secondPeriodStart);
        report.setSecond_period_end(secondPeriodEnd);
        report.setThird_period_start(thirdPeriodStart);
        report.setThird_period_end(thirdPeriodEnd);
        report.setPortolio_id(portfolio_id);
        report.setConsultant_id((int) session.getAttribute("id_conseiller"));
        this.reportDao.createReport(report);
        response.sendRedirect("/generateReport");


    }
}