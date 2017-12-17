/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.yakovlev.pavel.imdb_browser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.sql.*;

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
    private Label typeLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label numRatingsLabel;

    @FXML
    private Label movieSummaryLabel;

    @FXML
    private Label genreLabel;

    @FXML
    public void onEnter(ActionEvent event) throws Exception
    {
        // TODO: 12/12/2017 need throw exception if search fails

        String movieTitle = searchBar.getText().replaceAll("\\s+","");
        Movie someMovie = new Movie();
        APIRequest API = new APIRequest(movieTitle);
        API.send(someMovie);

        //DEBUG:
        System.out.println(someMovie.toString());

        if(someMovie.getName() == "")
        {
            movieNameLabel.setText("No results found!");
            typeLabel.setText("");
            ratingLabel.setText("");
            numRatingsLabel.setText("");
            yearLabel.setText("");
            movieSummaryLabel.setText("");
            genreLabel.setText("");
            moviePosterView.setImage(null);
        }
        else
        {
            movieNameLabel.setText(someMovie.getName());
            typeLabel.setText(someMovie.getType());
            ratingLabel.setText(someMovie.getRating() + " out of 10 stars");
            numRatingsLabel.setText(someMovie.getNumRatings() + " total ratings");
            yearLabel.setText(" " + someMovie.getYear());
            //genreLabel.setText(someMovie.getGenre());
            movieSummaryLabel.setText(someMovie.getSummary());

            ImageView moviePoster = new ImageView(someMovie.getPoster());
            moviePosterView.setImage(moviePoster.getImage());
        }
        // TODO: 12/12/2017 expand movie artwork on click
    }

    @FXML
    public void onPress(ActionEvent event) throws Exception
    {
        Class.forName("org.sqlite.JDBC");

        Connection conn = null;

        String connectionString = "jdbc:sqlite:database/MyMovies.sqlite3";

        conn = DriverManager.getConnection(connectionString);

        Statement statement = null;

        statement = conn.createStatement();

        statement.setQueryTimeout(20);


        String sql;

        sql = "INSERT INTO MyMovies ('Name', 'Type', 'Genre', 'Year', 'Reviews',  'NumReviews') " +
                "VALUES ('', '123-4567')";

        statement.execute(sql);

        sql = "SELECT * FROM Friends";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next())
        {
            String r;
            r = String.format("ID: %s, Name: %s, Phone: %s",
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getString("Phone"));


            System.out.println(r);
        }
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
