/*
Pavel Yakovlev
pyakovlev@bearcubs.santarosa.edu
29.12.2017
Final Project - IMDb Browser Application
Java 17.11
This program lets the user to look up a movie from IMDb and add it to a watchlist.
 */

package edu.srjc.yakovlev.pavel.imdb_browser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("IMDbBrowser.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop()throws Exception
    {
        System.out.println("Program is closing");
        System.out.println("Deleting entries from table...");

        Connection conn = null;
        String connectionString = "jdbc:sqlite:database/MyMovies.sqlite3";
        conn = DriverManager.getConnection(connectionString);
        Statement statement = null;
        statement = conn.createStatement();
        statement.setQueryTimeout(20);
        String sql;

        sql = "DELETE FROM Movies";

        statement.execute(sql);
        sql = "SELECT * FROM Movies";
        ResultSet rs = statement.executeQuery(sql);

        conn.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
