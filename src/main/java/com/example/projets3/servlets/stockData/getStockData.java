package com.example.projets3.servlets.stockData;

import java.io.*;

import java.util.ArrayList;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import com.example.projets3.servlets.stockData.apiCommunication;
@WebServlet(name = "getStockData", value = "/getData")
public class getStockData extends HttpServlet {
    private apiCommunication apiCommunication;
    public void init(){
        this.apiCommunication = new apiCommunication();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String date = request.getParameter("date");
        String stock = request.getParameter("name");
        //String symbole = this.apiCommunication.getStockSymbole(stock);
        ArrayList<ArrayList<String>> data = null;
        ArrayList<ArrayList<String>> results = null;
        /*
        if(date != null){
             data = this.apiCommunication.getStockData(symbole, "TIME_SERIES_DAILY");
            if (Objects.equals(date, "weekly")) {
                results = new ArrayList<>(data.subList(Math.max(0, data.size() - 5), data.size()));
            } else {
                results = new ArrayList<>(data.subList(Math.max(0, data.size() - 15), data.size()));
            }

        } else {
            results = this.apiCommunication.getStockData(symbole, "TIME_SERIES_INTRADAY");

        }

        request.setAttribute("stockName", stock);
        request.setAttribute("data", results);

         */
        request.getRequestDispatcher("stockPage.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String date = request.getParameter("date");
        String stock = request.getParameter("stock");
        if (Objects.equals(date, "weekly")){
            response.sendRedirect("/stock?name="+stock+"&date="+date);
        }else {
            response.sendRedirect("/stock?name="+stock+"&date="+"TIME_SERIES_INTRADAY");
        }
    }
    public void destroy() {
    }
}
