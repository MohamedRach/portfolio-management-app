package com.example.projets3.servlets.report;

import com.example.projets3.bean.reportBean;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.reportDao.reportDao;
import com.example.projets3.dao.reportDao.reportDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


@WebServlet(name = "generateReport", value = "/generateReport")
public class generateReport extends HttpServlet {
    private reportDao reportDao;
    public void init(){
        daoFactory dao = com.example.projets3.dao.daoFactory.getInstance();
        this.reportDao = new reportDaoImpl(dao);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("generateReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String firstPeriodStart =  request.getParameter("firstPeriodStart");
        String firstPeriodEnd =   request.getParameter("firstPeriodEnd");
        String secondPeriodStart =   request.getParameter("secondPeriodStart");
        String secondPeriodEnd =   request.getParameter("secondPeriodEnd");
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
        report.setPortolio_id(1);
        report.setConsultant_id(1);
        this.reportDao.createReport(report);
        response.sendRedirect("/generateReport");


    }
}