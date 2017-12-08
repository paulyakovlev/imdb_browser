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
        String movieTitle = null;
        String apiURL = "http://voku.xyz/imdb/api";
        String requestURL = null;

        Scanner inputStream = new Scanner(System.in);

        System.out.println("Enter name of movie:");

        movieTitle = inputStream.nextLine();

        requestURL = apiURL + "?q=" + movieTitle;

        URL aWebPage = new URL(requestURL);

        HttpURLConnection letsSee = (HttpURLConnection) aWebPage.openConnection();

        BufferedReader BringItIn = new BufferedReader(new InputStreamReader(letsSee.getInputStream()));

        String httpCode;

        while((httpCode = BringItIn.readLine()) != null)
        {
            if (httpCode.isEmpty() != true)
            {
                System.out.println(httpCode);
            }
            else
            {
                System.out.println("There is nothing to print");
            }
        }
        BringItIn.close();
    }
}

