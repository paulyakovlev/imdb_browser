package edu.srjc.yakovlev.pavel.imdb_browser;

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String movieTitle = "";
        Scanner inputStream = new Scanner(System.in);
        Movie someMovie = new Movie();

        System.out.println("Enter name of movie:");
        movieTitle = inputStream.nextLine().replaceAll("\\s+","");

        APIRequest API = new APIRequest(movieTitle);
        API.send(someMovie);

        System.out.println(someMovie.toString());
    }
}

