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

    public void send(Movie someMovie) throws Exception
    {
        String apiURL = "http://voku.xyz/imdb/api";
        String apiResponse = "";

        String requestURL = apiURL + "?q=" + movieTitle;
        URL apiLocation = new URL(requestURL);

        HttpURLConnection connection = (HttpURLConnection) apiLocation.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((apiResponse = reader.readLine()) != null)
        {
            if (!apiResponse.isEmpty())
            {
             //   System.out.println(apiResponse);
                getJSON(apiResponse,someMovie);
            }
            else
            {
                System.out.println("There is nothing to print");
            }
        }
        reader.close();
    }

    private void getJSON (String JSONinput,Movie someMovie)
    {
        String key = JSONinput.split(":")[0].replaceAll("\\s+","");
        key = key.replace("\"","");

        String value = JSONinput.split(",")[0].replaceAll(".*:","");
        value = value.replaceAll("\\s+","").replace("\"","");

        switch (key)
        {
            case "name":
                someMovie.setName(value);
                break;
            case "rating":
                someMovie.setRating(value);
                break;
            case "summary":
                someMovie.setSummary(value);
            case "poster":
                someMovie.setPoster(value);
        }

//        System.out.println("key:" + key);
//        System.out.println("value:" + value);
//        System.out.println(someMovie.toString());
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

