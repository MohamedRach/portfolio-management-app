package com.example.projets3.servlets.report;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class apiCommunication {
    public double getFinancials(String symbole, String start_period, String end_period){
        String url_req = "https://eodhd.com/api/eod/"+symbole+".US?from="+start_period+"&to="+end_period+"&period=d&api_token=65c17abe941ca2.61621139&fmt=json";
        //System.out.println(url_req);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_req))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        double data = 0;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            data = this.getFinancialsParser(response);


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    private double getFinancialsParser(HttpResponse<String> response){
        JSONParser parser = new JSONParser();
        ArrayList<String> data = new ArrayList<String>();
        double result = 0;


        try {
            JSONArray jsonArray = (JSONArray) parser.parse(response.body());
            for(int i = 0; i < jsonArray.size();  i++){
                JSONObject dt = (JSONObject) jsonArray.get(i);
                result += (double) dt.get("close");
            }



        }catch(ParseException pe) {
            throw new RuntimeException(pe);
        }

        return result;
    }
}
