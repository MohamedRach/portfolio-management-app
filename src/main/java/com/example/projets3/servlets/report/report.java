package com.example.projets3.servlets.report;

import com.example.projets3.bean.reportBean;
import com.example.projets3.bean.stockBean;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.reportDao.reportDao;
import com.example.projets3.dao.stock.stockDao;
import com.example.projets3.dao.reportDao.reportDaoImpl;
import com.example.projets3.dao.stock.stockDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "report", value = "/report")
public class report extends HttpServlet {
    private reportDao reportDao;
    private stockDao stockDao;
    private apiCommunication apiCommunication;
    public void init(){
        daoFactory dao = com.example.projets3.dao.daoFactory.getInstance();
        this.reportDao = new reportDaoImpl(dao);
        this.stockDao = new stockDaoImpl(dao);
        this.apiCommunication = new apiCommunication();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int report_id = Integer.parseInt(request.getParameter("id"));
        reportBean report = this.reportDao.getReportById(report_id);
        ArrayList<stockBean> stocks = this.stockDao.getStocks(report.getPortolio_id());
        double first_period_return = 0;
        double second_period_return = 0;
        double third_period_return = 0;
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        for(stockBean stock: stocks) {
            ArrayList<Object> dt = new ArrayList<>();
            double return1 = this.apiCommunication.getFinancials(stock.getName(), report.getFirst_period_start(), report.getFirst_period_end());
            double return2 = this.apiCommunication.getFinancials(stock.getName(), report.getSecond_period_start(), report.getSecond_period_end());
            double return3 = this.apiCommunication.getFinancials(stock.getName(), report.getThird_period_start(), report.getThird_period_end());
            first_period_return += return1;
            second_period_return += return2;
            third_period_return += return3;
            dt.add(stock.getName());
            dt.add(return1);
            dt.add(return2);
            dt.add(return3);
            data.add(dt);
        }
        String first_period = report.getFirst_period_start() + " to  " + report.getFirst_period_end();
        String second_period = report.getSecond_period_start() + " to " + report.getSecond_period_end();
        String third_period = report.getThird_period_start() + " to " + report.getThird_period_end();
        request.setAttribute("reportName", report.getName());
        request.setAttribute("first_period", first_period);
        request.setAttribute("second_period", second_period);
        request.setAttribute("third_period", third_period);
        request.setAttribute("first_period_return", first_period_return);
        request.setAttribute("second_period_return", second_period_return);
        request.setAttribute("third_period_return", third_period_return);
        request.setAttribute("data", data);
        request.getRequestDispatcher("report.jsp").forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}