package service;

import model.Movie;
import java.util.*;

public class MovieManager {
    private List<Movie> movieList;

    public MovieManager() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public boolean deleteMovie(String title) {
        return movieList.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

    public boolean editMovie(String title, String newStatus, Double newRating) {
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                if (newStatus != null) m.setStatus(newStatus);
                if (newRating != null) m.setRating(newRating);
                return true;
            }
        }
        return false;
    }

    public List<Movie> getAllMovies() {
        return movieList;
    }

    public List<Movie> filterByGenre(String genre) {
        List<Movie> filtered = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(m);
            }
        }
        return filtered;
    }

    public void sortByRating(boolean descending) {
        movieList.sort(descending ?
            Comparator.comparingDouble(Movie::getRating).reversed() :
            Comparator.comparingDouble(Movie::getRating));
    }
}
