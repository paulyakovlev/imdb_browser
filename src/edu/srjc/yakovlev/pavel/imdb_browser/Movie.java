package edu.srjc.yakovlev.pavel.imdb_browser;

public class Movie
{
    private String name = "";
    private String summary = "";
    private String poster = "";
    private String genres = "";
    private String year = "";
    private String numRatings = "";
    private String rating = "";

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

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getNumRatings()
    {
        return numRatings;
    }

    public void setNumRatings(String numRatings)
    {
        this.numRatings = numRatings;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        return "name:" + name + "\n" +
                "summary:" + summary + "\n" +
                "poster:" + poster + "\n" +
                "genres:" + genres + "\n" +
                "year:" + year + "\n" +
                "numRatings:" + numRatings + "\n" +
                "rating:" + rating + "\n";
    }
}
