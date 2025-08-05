package src.model;

public class Movie {
    private String title;
    private String genre;
    private String status; // "Watched" or "Not Watched"
    private double rating; // 0.0 to 10.0

    public Movie(String title, String genre, String status, double rating) {
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.rating = rating;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public double getRating() {
        return rating;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-12s %.1f", title, genre, status, rating);
    }
}
