package edu.srjc.yakovlev.pavel.imdb_browser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIRequest
{
    private String movieTitle = "";

    public APIRequest(String movieTitle)
    {
        setMovieTitle(movieTitle);
    }

    public void send() throws Exception
    {
        String apiURL = "http://voku.xyz/imdb/api";
        String httpCode = "";

        String requestURL = apiURL + "?q=" + movieTitle;
        URL aWebPage = new URL(requestURL);

        HttpURLConnection letsSee = (HttpURLConnection) aWebPage.openConnection();

        BufferedReader BringItIn = new BufferedReader(new InputStreamReader(letsSee.getInputStream()));

        while ((httpCode = BringItIn.readLine()) != null)
        {
            if (!httpCode.isEmpty()) {
                System.out.println(httpCode);
            } else {
                System.out.println("There is nothing to print");
            }
        }
        BringItIn.close();
    }

    public String getMovieTitle()
    {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle)
    {
        this.movieTitle = movieTitle;
    }
}

