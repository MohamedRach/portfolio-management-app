package com.example.projets3.servlets.stockData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class apiCommunication {
    public String getStockSymbole(String stockName){
        HttpRequest search_request = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?keywords=" + stockName +"&function=SYMBOL_SEARCH&datatype=json"))
                .header("X-RapidAPI-Key", "603c9d84f1msh0c5a4c9663e3137p1b165bjsn73e9bf65892c")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> search_response = null;
        String symbol = null;
        try {
            search_response = HttpClient.newHttpClient().send(search_request, HttpResponse.BodyHandlers.ofString());
            symbol = this.stockNameParser(search_response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return symbol;
    }

    private String stockNameParser(HttpResponse<String> response) {
        JSONParser parser = new JSONParser();
        String symbol = null;
        try {
            JSONObject obj = (JSONObject) parser.parse(response.body());
            JSONArray bestMatches = (JSONArray) obj.get("bestMatches");
            JSONObject first_matche = (JSONObject) bestMatches.get(0);
            symbol = (String) first_matche.get("1. symbol");

        }catch(ParseException pe) {
            throw new RuntimeException(pe);
        }
        return symbol;
    }

    public ArrayList<ArrayList<String>> getStockData(String symbole, String date) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?interval=5min&function="+date+"&symbol="+ symbole +"&datatype=json&output_size=compact"))
                .header("X-RapidAPI-Key", "603c9d84f1msh0c5a4c9663e3137p1b165bjsn73e9bf65892c")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> search_response = null;
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            search_response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
            if(Objects.equals(date, "TIME_SERIES_INTRADAY")){
                data = this.stockDataParser(search_response, "Time Series (5min)");
            } else {
                data = this.stockDataParser(search_response, "Time Series (Daily)");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private ArrayList<ArrayList<String>> stockDataParser(HttpResponse<String> response, String field){
        JSONParser parser = new JSONParser();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            JSONObject obj = (JSONObject) parser.parse(response.body());
            JSONObject field1 = (JSONObject) obj.get(field);


            Set keys = field1.keySet();
            Iterator<String> keysIterator = keys.iterator();
            while(keysIterator.hasNext()) {
                JSONObject dataObject = (JSONObject) field1.get(keysIterator.next());
                ArrayList<String> DateAndPrice = new ArrayList<>();
                DateAndPrice.add((String) keysIterator.next());
                DateAndPrice.add((String) dataObject.get("4. close"));
                data.add(DateAndPrice);

            }
        } catch(ParseException pe) {
            throw new RuntimeException(pe);
        }
        data = this.sortArray(data);
        System.out.println(data);
        return data;

    }

    private ArrayList<ArrayList<String>> sortArray(ArrayList<ArrayList<String>> data) {
        Collections.sort(data, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> list1, ArrayList<String> list2) {
                // Assuming the first element of each inner ArrayList is of type String
                String date1 = (String) list1.get(0);
                String date2 = (String) list2.get(0);

                // Custom date comparison logic
                return date1.compareTo(date2);
            }
        });
        return data;
    }
}
