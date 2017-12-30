package edu.srjc.yakovlev.pavel.imdb_browser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
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
        // TODO: 12/13/2017 if can't connect?
        // TODO: 12/13/2017 if can't find any results?
        String apiURL = "http://www.omdbapi.com/?t=";
        String apiKey = "&apikey=4ff78d79";
        String apiResponse = "";

        movieTitle = movieTitle.replaceAll("\\s","\\+");
        String requestURL = apiURL + movieTitle + apiKey;
        URL apiLocation = new URL(requestURL);

        //DEBUG:
        System.out.println("Sending request to: " + requestURL);

        try
        {
            HttpURLConnection connection = (HttpURLConnection) apiLocation.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((apiResponse = reader.readLine()) != null)
            {
                if (!apiResponse.isEmpty())
                {
                    //DEBUG:
                    System.out.println("Response: " + apiResponse);
                    getJSON(apiResponse,someMovie);
                }
                else
                {
                    System.out.println("There is nothing to print");
                }
            }
            reader.close();
        }
        catch(ConnectException e)
        {
            System.out.println("Could not establish connection!");
        }
    }

    private void getJSON (String JSONinput,Movie someMovie)
    {
        String[] pair = JSONinput.split("\"");
        int count = 0;

        //DEBUG:
        for (String s: pair)
        {
            //DEBUG:
            System.out.println(s);

            switch (s)
            {
                case "Type":
                    String type = pair[count + 2].replaceAll(",","");
                    someMovie.setType(type);
                    if(!type.equals("movie"))
                    {
                        someMovie.setMovie(false);
                    }
                    count++;
                    break;
                case "Title":
                    String title = pair[count + 2].replaceAll(",","");
                    someMovie.setName(title);
                    count++;
                    break;
                case "Year":
                    String year = pair[count + 2].replaceAll(",","");
                    someMovie.setYear(year);
                    count++;
                    break;
                case "Rated":
                    String rating = pair[count + 2].replaceAll(",","");
                    someMovie.setRating(rating);
                    count++;
                    break;
                case "Runtime":
                    String runTime = pair[count + 2].replaceAll(",","");
                    //someMovie.setRunTime(runTime);
                    count++;
                    break;
                case "Genre":
                    String genre = pair[count + 2].replaceAll(",","");
                    someMovie.setGenre(genre);
                    count++;
                    break;
                case "Plot":
                    String summary = pair[count + 2].replaceAll(",","");
                    someMovie.setSummary(summary);
                    count++;
                    break;
                case "Poster":
                    String  poster = pair[count + 2].replaceAll(",","");
                    someMovie.setPoster(poster);
                    count++;
                    break;
                case "imdbRating":
                    String  imdbRating = pair[count + 2].replaceAll(",","");
                    someMovie.setImdbRating(imdbRating);
                    count++;
                    break;
                case "imdbVotes":
                    String numRatings = pair[count + 2].replaceAll(",","");
                    someMovie.setNumRatings(numRatings);
                    count++;
                    break;
                default:
                    count++;
                    break;
//                case "numRatings":
//                    String numRatings = value.replaceAll(",$","");
//                    someMovie.setNumRatings(numRatings);
//                    break;
//                case "rating":
//                    String rating = value.replaceAll(",","");
//                    someMovie.setRating(rating);
//                    break;
//                case "summary":
//                    String summary = value.replaceAll(",$","");
//                    someMovie.setSummary(summary);
//                    break;
//                case "poster":
//                    String poster = JSONinput.replaceAll(",","").replaceAll(".*: ","");
//                    poster = poster.replace("\"","");
//                    someMovie.setPoster(poster);
//                    break;
        }

        // TODO: 12/12/2017 any way to clean up regex?

            // TODO: 12/13/2017 what if type is tv show?
            // TODO: 12/13/2017 what if type is neither movie nor tv show?
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

