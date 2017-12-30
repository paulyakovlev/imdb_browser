package edu.srjc.yakovlev.pavel.imdb_browser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.sql.*;

public class IMDbBrowserController implements Initializable
{
    private String currentMovie = "";

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
    private Button watchlistButton;

    @FXML
    public void onEnter(ActionEvent event) throws Exception
    {
        // TODO: 12/12/2017 need throw exception if search fails
        // TODO: 12/28/2017 someMovie is being generated every time here...

        Movie someMovie = new Movie();

        if (!currentMovie.equals(""))
        {
            String sql = "DELETE FROM Movies WHERE Name = '"+ currentMovie +"'";
            doSQL(sql);
        }

        String movieTitle = searchBar.getText();
        APIRequest API = new APIRequest(movieTitle);
        API.send(someMovie);

        //DEBUG:
        System.out.println(someMovie.toString());

        if(someMovie.getName().equals(""))
        {
            currentMovie = "";

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
            currentMovie = someMovie.getName();

            String sql = "INSERT INTO Movies ('Name', 'Type', 'Genre', 'Year', 'Reviews', 'NumReviews') " +
                    "VALUES ('"+someMovie.getName()+"', '"+someMovie.getType()+"', '"+someMovie.getGenre()+"'," +
                    "'"+someMovie.getYear()+"', '"+someMovie.getImdbRating()+"', '"+someMovie.getNumRatings()+"')";

            doSQL(sql);

            movieNameLabel.setText(someMovie.getName());
            typeLabel.setText(someMovie.getType());
            ratingLabel.setText(someMovie.getImdbRating() + " out of 10 stars");
            numRatingsLabel.setText(someMovie.getNumRatings() + " total ratings");
            yearLabel.setText(someMovie.getYear());
            genreLabel.setText(someMovie.getGenre());
            movieSummaryLabel.setText(someMovie.getSummary());

            ImageView moviePoster = new ImageView(someMovie.getPoster());
            moviePosterView.setImage(moviePoster.getImage());
        }

        if (isInWatchlist(currentMovie))
        {
            watchlistButton.setText("remove from watchlist");
        }
        else
        {
            watchlistButton.setText("add to watchlist");
        }

        // TODO: 12/12/2017 expand movie artwork on click
    }

    @FXML
    public void onPress(ActionEvent event) throws Exception
    {
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("Watchlist.fxml"));
//        Scene scene = new Scene(root)
//        stage.setScene(scene);
//        stage.show();
//        Class.forName("org.sqlite.JDBC");

        if (watchlistButton.getText().equals("add to watchlist"))
        {
            watchlistButton.setText("remove from watchlist");

            String sql = "INSERT INTO Watchlist SELECT * FROM Movies WHERE Name = '"+currentMovie+"'";
            doSQL(sql);
        }
        else
        {
            watchlistButton.setText("add to watchlist");

            String sql = "DELETE FROM Watchlist WHERE Name = '"+ currentMovie +"'";
            doSQL(sql);
        }


    }

    public void doSQL(String sql)throws Exception
    {
        Connection conn = null;
        String connectionString = "jdbc:sqlite:database/MyMovies.sqlite3";
        conn = DriverManager.getConnection(connectionString);
        Statement statement = null;
        statement = conn.createStatement();
        statement.setQueryTimeout(20);

        statement.execute(sql);

        sql = "SELECT * FROM Movies";
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("Movies Table:");

        while (rs.next())
        {
            String r;
            r = String.format("ID: %s, Name: %s, Type: %s, Genre: %s, Year: %s, Reviews: %s, NumReviews: %s",
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getString("Genre"),
                    rs.getString("Year"),
                    rs.getString("Reviews"),
                    rs.getString("NumReviews"));

            System.out.println(r);
        }

        sql = "SELECT * FROM Watchlist";
        rs = statement.executeQuery(sql);
        System.out.println("Watchlist Table:");
        while (rs.next())
        {
            String r;
            r = String.format("ID: %s, Name: %s, Type: %s, Genre: %s, Year: %s, Reviews: %s, NumReviews: %s",
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getString("Genre"),
                    rs.getString("Year"),
                    rs.getString("Reviews"),
                    rs.getString("NumReviews"));

            System.out.println(r);
        }
        conn.close();
    }

    public boolean isInWatchlist(String name)throws Exception
    {
        String sql = "SELECT 1 WHERE EXISTS(SELECT * FROM Watchlist WHERE Name = '"+name+"' LIMIT 1) ";
        Connection conn = null;
        String connectionString = "jdbc:sqlite:database/MyMovies.sqlite3";
        conn = DriverManager.getConnection(connectionString);
        Statement statement = null;
        statement = conn.createStatement();
        statement.setQueryTimeout(20);

        ResultSet rs = statement.executeQuery(sql);
        boolean inWatchlist = rs.next();
        System.out.println("in watchlist?: " + inWatchlist);

        conn.close();
        return inWatchlist;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
