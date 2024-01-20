package com.example.projets3.servlets;

import java.io.*;

import java.util.ArrayList;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
@WebServlet(name = "getStockData", value = "/getData")
public class getStockData extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String stock = request.getParameter("name");
        HttpRequest search_request = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?keywords=" + stock +"&function=SYMBOL_SEARCH&datatype=json"))
                .header("X-RapidAPI-Key", "603c9d84f1msh0c5a4c9663e3137p1b165bjsn73e9bf65892c")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> search_response = null;
        try {
            search_response = HttpClient.newHttpClient().send(search_request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(search_response.body());
        JSONParser parser = new JSONParser();
        String symbol = null;
        try {
            JSONObject obj = (JSONObject) parser.parse(search_response.body());
            JSONArray bestMatches = (JSONArray) obj.get("bestMatches");
            JSONObject first_matche = (JSONObject) bestMatches.get(0);
            symbol = (String) first_matche.get("1. symbol");

        }catch(ParseException pe) {

            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?interval=5min&function=TIME_SERIES_INTRADAY&symbol="+ symbol+"&datatype=json&output_size=compact"))
                .header("X-RapidAPI-Key", "603c9d84f1msh0c5a4c9663e3137p1b165bjsn73e9bf65892c")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            try {
                HttpResponse<String> res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
                JSONObject obj = (JSONObject) parser.parse(res.body());
                JSONObject field1 = (JSONObject) obj.get("Time Series (5min)");


                Set keys = field1.keySet();
                Iterator<String> keysIterator = keys.iterator();
                while(keysIterator.hasNext()) {
                    JSONObject dataObject = (JSONObject) field1.get(keysIterator.next());
                    ArrayList<String> DateAndPrice = new ArrayList<>();
                    DateAndPrice.add((String) keysIterator.next());
                    DateAndPrice.add((String) dataObject.get("4. close"));
                    data.add(DateAndPrice);
                    //System.out.println(field1.get(keysIterator.next()));
                }


                request.setAttribute("stockName", stock);
                request.setAttribute("data", data);
                request.getRequestDispatcher("stockPage.jsp").forward(request, response);

                //System.out.println(keys);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ParseException pe) {
                throw new RuntimeException(pe);
            } catch (ServletException s) {
                throw new RuntimeException(s);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }





    }

    public void destroy() {
    }
}
