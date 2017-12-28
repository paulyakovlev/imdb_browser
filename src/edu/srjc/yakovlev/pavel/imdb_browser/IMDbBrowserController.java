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

        Movie someMovie = new Movie();
        String movieTitle = searchBar.getText().replaceAll("\\s+","");
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
            genreLabel.setText(someMovie.getGenre());
            movieSummaryLabel.setText(someMovie.getSummary());

            ImageView moviePoster = new ImageView(someMovie.getPoster());
            moviePosterView.setImage(moviePoster.getImage());
        }
        // TODO: 12/12/2017 expand movie artwork on click
    }

//    @FXML
//    public void onPress(ActionEvent event, Movie someMovie) throws Exception
//    {
//        Class.forName("org.sqlite.JDBC");
//
//        Connection conn = null;
//
//        String connectionString = "jdbc:sqlite:database/MyMovies.sqlite3";
//
//        conn = DriverManager.getConnection(connectionString);
//
//        Statement statement = null;
//
//        statement = conn.createStatement();
//
//        statement.setQueryTimeout(20);
//
//        String sql;
//
//        sql = "INSERT INTO Movies ('Name', 'Type', 'Genre', 'Year', 'Reviews', 'NumReviews') " +
//                "VALUES ( , 'Type', 'Genre', 'Year', 'Reviews', 'NumReviews')";
//
//        statement.execute(sql);
//
//        sql = "SELECT * FROM Movies";
//
//        ResultSet rs = statement.executeQuery(sql);
//
//        while (rs.next())
//        {
//            String r;
//            r = String.format("ID: %s, Name: %s, Type: %s",
//                    rs.getLong("ID"),
//                    rs.getString("Name"),
//                    rs.getString("Type"));
//
//            System.out.println(r);
//        }
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
