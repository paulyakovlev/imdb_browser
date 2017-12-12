/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.yakovlev.pavel.imdb_browser;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.*;


/**
 *
 * @author 
 */
public class IMDbBrowserController implements Initializable
{
    
    @FXML
    private TextField searchBar;

    @FXML
    private ImageView moviePosterView;

    @FXML
    private Label movieNameLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label numRatingsLabel;

    @FXML
    private Label movieSummaryLabel;

    @FXML
    private ScrollPane movieSummaryPane;

    @FXML
    public void onEnter(ActionEvent event) throws Exception
    {
        String movieTitle = searchBar.getText().replaceAll("\\s+","");
       // Scanner inputStream = new Scanner(System.in);
        Movie someMovie = new Movie();

      //  System.out.println("Enter name of movie:");
      //  movieTitle = inputStream.nextLine().replaceAll("\\s+","");

        APIRequest API = new APIRequest(movieTitle);
        API.send(someMovie);

        System.out.println(someMovie.toString());

        ImageView moviePoster = new ImageView(someMovie.getPoster());

        movieNameLabel.setText(someMovie.getName());
        ratingLabel.setText(someMovie.getRating() + " out of 10 stars");
        numRatingsLabel.setText(someMovie.getNumRatings() + " total ratings");
        yearLabel.setText(" " + someMovie.getYear());
        movieSummaryLabel.setText(someMovie.getSummary());
        moviePosterView.setImage(moviePoster.getImage());
        // TODO: 12/11/2017 pull up poster in imageview from url
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
