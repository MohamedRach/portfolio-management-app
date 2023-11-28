package com.example.projets3.servlets.authentification;

import com.example.projets3.dao.DAOConfigurationException;
import com.example.projets3.dao.daoFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class googleAuth {
    private static final String FICHIER_PROPERTIES       = "dao.properties";
    private static final String PROPERTY_GOOGLE_CLIENT_ID            = "client_id";
    private static final String PROPERTY_GOOGLE_CLIENT_SECRET         = "client_secret";
    private static final String PROPERTY_googleOauthRedirectUrl = "http://localhost:8080";
    private static final String PROPERTY_SERVER_ROOT_URI    = "http://localhost:8080";
    private String GOOGLE_CLIENT_ID;
    private String GOOGLE_CLIENT_SECRET;
    private String googleOauthRedirectUrl;
    private String SERVER_ROOT_URI;

    public googleAuth(String google_client_id, String google_client_secret, String google_redirect_url, String server_root_uri) {
        super();
        this.GOOGLE_CLIENT_ID = google_client_id;
        this.GOOGLE_CLIENT_SECRET= google_client_secret;
        this.SERVER_ROOT_URI = server_root_uri;
        this.googleOauthRedirectUrl = google_redirect_url;
    }
    public static googleAuth getInstance() {
        Properties properties = new Properties();
        String GOOGLE_CLIENT_ID;
        String GOOGLE_CLIENT_SECRET ;
        String googleOauthRedirectUrl;
        String SERVER_ROOT_URI;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            GOOGLE_CLIENT_ID = "572365298482-p1u26gvpalnfmt2bteteqgtjno4lhlmh.apps.googleusercontent.com";
            GOOGLE_CLIENT_SECRET = "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";
            googleOauthRedirectUrl = "auth/google";
            SERVER_ROOT_URI = "http://localhost:8080";
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }


        googleAuth instance = new googleAuth(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, googleOauthRedirectUrl, SERVER_ROOT_URI);
        return instance;
    }

    public String generateUrl() {
        String rootUrl = "https://accounts.google.com/o/oauth2/v2/auth";

        try {
            // URL encode the redirect_uri
            String encodedRedirectUri = URLEncoder.encode(this.SERVER_ROOT_URI + "/" + this.googleOauthRedirectUrl, "UTF-8");

            // Build the query parameters


            String options = String.format(
                    "redirect_uri=%s&client_id=%s&access_type=offline&response_type=code&prompt=consent&scope=%s",
                    encodedRedirectUri,
                    this.GOOGLE_CLIENT_ID,
                    URLEncoder.encode("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email", "UTF-8")
            );

            // Build the final URL
            return rootUrl + "?" + options;

        } catch (UnsupportedEncodingException e) {
            // Handle encoding exception
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getToken(String Code) {
        String url =  "https://oauth2.googleapis.com/token";
        try {
            String encodedRedirectUri = URLEncoder.encode(this.SERVER_ROOT_URI + "/" + this.googleOauthRedirectUrl, "UTF-8");

            String options = String.format(
                    "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                    Code,
                    this.GOOGLE_CLIENT_ID,
                   this.GOOGLE_CLIENT_SECRET,
                    encodedRedirectUri
            );
            HttpRequest search_request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "?" + options))
                    .header("Content-type", "application/x-www-form-urlencoded")
                    .method("POST", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> search_response = null;
            ArrayList<String> tokens = new ArrayList<String>();
            try {
                search_response = HttpClient.newHttpClient().send(search_request, HttpResponse.BodyHandlers.ofString());

                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(search_response.body());
                tokens.add((String) obj.get("access_token"));
                tokens.add((String) obj.get("id_token"));
                return tokens;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException f){
                throw new RuntimeException(f);
            } catch (ParseException pe){
                throw new RuntimeException(pe);
            }
        }catch (UnsupportedEncodingException e) {
            // Handle encoding exception
            e.printStackTrace();

        }


        return null;
    }
}
