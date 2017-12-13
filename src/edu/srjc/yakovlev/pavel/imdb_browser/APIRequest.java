package edu.srjc.yakovlev.pavel.imdb_browser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
//              DEBUG:
                System.out.println(apiResponse);
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

        String value = JSONinput.replaceAll(".*\":","");
        value = value.replace("\"","");

        // TODO: 12/12/2017 any way to clean up regex
        switch (key)
        {
            case "name":
                String name = value.replaceAll("<.*","");
                someMovie.setName(name);
                String year = value.replaceAll(".*year/","").replaceAll("/\\?.*","");
                someMovie.setYear(year);
                break;
            case "numRatings":
                String numRatings = value.replaceAll(",$","");
                someMovie.setNumRatings(numRatings);
                break;
            case "rating":
                String rating = value.replaceAll(",","");
                someMovie.setRating(rating);
                break;
            case "summary":
                String summary = value.replaceAll(",$","");
                someMovie.setSummary(summary);
                break;
            case "poster":
                String poster = JSONinput.replaceAll(",","").replaceAll(".*: ","");
                poster = poster.replace("\"","");
                someMovie.setPoster(poster);
                break;
            // TODO: 12/11/2017 need to get genres - put into arraylist?
        }

//        DEBUG:
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

