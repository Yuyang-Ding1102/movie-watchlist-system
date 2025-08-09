package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Movie;


public class MovieManager {
    private final List<Movie> movieList;

    public MovieManager() {
        this.movieList = new ArrayList<>();
    }

    // Add a new movie
    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    // Delete a movie by title
    public boolean deleteMovie(String title) {
        return movieList.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

    // Update movie status and/or rating
    public boolean editMovie(String title, String newStatus, Double newRating) {
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                if (newStatus != null && !newStatus.isEmpty()) m.setStatus(newStatus);
                if (newRating != null) m.setRating(newRating);
                return true;
            }
        }
        return false;
    }

    // Get all movies (returns the live list)
    public List<Movie> getAllMovies() {
        return movieList;
    }

    // Filter by genre
    public List<Movie> filterByGenre(String genre) {
        List<Movie> filtered = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(m);
            }
        }
        return filtered;
    }

    // Sort by rating (ascending = false / descending = true)
    public void sortByRating(boolean descending) {
        movieList.sort(descending
                ? Comparator.comparingDouble(Movie::getRating).reversed()
                : Comparator.comparingDouble(Movie::getRating));
    }
    
        // Mark a movie as watched by title
    public boolean markAsWatched(String title) {
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                m.setStatus("Watched");
                return true;
            }
        }
        return false;
    }

}

