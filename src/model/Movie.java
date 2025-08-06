package model;

public class Movie {
    private String title;
    private String genre;
    private String status;
    private double rating;

    public Movie(String title, String genre, String status, double rating) {
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }
    public double getRating() { return rating; }

    public void setStatus(String status) { this.status = status; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-12s %.1f", title, genre, status, rating);
    }
}
