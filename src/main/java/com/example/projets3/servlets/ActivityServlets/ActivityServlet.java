package com.example.projets3.servlets.ActivityServlets;

import java.io.*;
import java.util.ArrayList;

import com.example.projets3.bean.ActivityBean;
import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.projets3.dao.daoFactory;
import com.example.projets3.dao.ConseillerDao.ActivityDao;

@WebServlet(name = "activityServlet", value = "/activityList")
public class ActivityServlet  extends HttpServlet {
    private ActivityDao activityDao;
    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.activityDao = new ActivityDaoImpl(daoFactory);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ActivityBean> activities = this.activityDao.getAllActivity();
        request.setAttribute("activities", activities);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/activities.jsp");
        dispatcher.forward(request, response);
    }
}
