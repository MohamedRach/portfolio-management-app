package com.example.projets3.servlets;

import java.io.*;
import java.util.ArrayList;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import com.example.projets3.bean.UserBean;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
@WebServlet(name = "getStockData", value = "/getData")
public class getStockData extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
            System.out.println(symbol);
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
        try {
            try {
                HttpResponse<String> res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
                System.out.println(res.body());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }




    }

    public void destroy() {
    }
}
