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
    private String type = "";
    private boolean isMovie = true;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
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
                "rating:" + rating + "\n" +
                "type:" + type + "\n" +
                "isMovie:" + isMovie + "\n";
    }
}
