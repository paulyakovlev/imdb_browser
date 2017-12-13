/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.yakovlev.pavel.imdb_browser;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    public void onEnter(ActionEvent event) throws Exception
    {
        // TODO: 12/12/2017 need throw exception if search fails

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

        // TODO: 12/12/2017 expand movie artwork on click
    }
//in progress.....open new large imageView on click
//    private void posterClicked(ActionEvent event) throws Exception
//    {
//            ImageView img = new ImageView("http://i.stack.imgur.com/oURrw.png");
//            img.setPickOnBounds(true); // allows click on transparent areas
//            img.setOnMouseClicked((MouseEvent e) -> {
//                System.out.println("Clicked!"); // change functionality
//            });
//            Scene scene = new Scene(new StackPane(img));
//            primaryStage.setTitle("Image Click Example");
//            primaryStage.setScene(scene);
//            primaryStage.sizeToScene();
//            primaryStage.show();
//        }
//
//        public static void main(String[] args) {
//            launch(args);
//        }
//
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
