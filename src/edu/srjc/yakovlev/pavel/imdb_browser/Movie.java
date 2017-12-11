package edu.srjc.yakovlev.pavel.imdb_browser;

public class Movie
{
    private String name;
    private String summary;
    private String poster;
    private String genres;
    private int year;
    private int numRatings;
    private double rating;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getPoster()
    {
        return poster;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public String getGenres()
    {
        return genres;
    }

    public void setGenres(String genres)
    {
        this.genres = genres;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getNumRatings()
    {
        return numRatings;
    }

    public void setNumRatings(int numRatings)
    {
        this.numRatings = numRatings;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        return "Movie{" +
                "name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", poster='" + poster + '\'' +
                ", genres='" + genres + '\'' +
                ", year=" + year +
                ", numRatings=" + numRatings +
                ", rating=" + rating +
                '}';
    }
}
