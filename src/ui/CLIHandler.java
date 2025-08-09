package ui;

import java.util.List;
import java.util.Scanner;
import model.Movie;
import service.MovieManager;
import service.StatisticsService;
import storage.StorageService;

public class CLIHandler {
    private static final String DATA_PATH = "data/movies.csv";

    private final MovieManager movieManager;
    private final StorageService storage;
    private final StatisticsService stats;
    private final Scanner scanner;

    public CLIHandler() {
        this.movieManager = new MovieManager();
        this.storage = new StorageService();
        this.stats = new StatisticsService();
        this.scanner = new Scanner(System.in);

        loadFromDisk(false);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addMovie();
                case "2" -> viewAllMovies();
                case "3" -> deleteMovie();
                case "4" -> editMovie();
                case "5" -> filterByGenre();
                case "6" -> sortMoviesByRating();
                case "7" -> saveToDisk(true);
                case "8" -> loadFromDisk(true);
                case "9" -> viewStatistics();
                case "10" -> markMovieAsWatched();
                case "0" -> {
                    saveToDisk(false);
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Exiting...");
    }

    private void printMenu() {
        System.out.println("\n=== Movie Watchlist Menu ===");
        System.out.println("1. Add movie");
        System.out.println("2. View all movies");
        System.out.println("3. Delete a movie");
        System.out.println("4. Edit a movie");
        System.out.println("5. Filter by genre");
        System.out.println("6. Sort movies by rating");
        System.out.println("7. Save to CSV");
        System.out.println("8. Load from CSV");
        System.out.println("9. View statistics");
        System.out.println("10. Mark movie as Watched");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void addMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();

        System.out.print("Status (Watched/Not Watched): ");
        String status = scanner.nextLine().trim();

        System.out.print("Rating (0.0 - 10.0): ");
        double rating = readDoubleOrDefault(0.0);

        movieManager.addMovie(new Movie(title, genre, status, rating));
        System.out.println("✔ Movie added.");
    }

    private void viewAllMovies() {
        List<Movie> movies = movieManager.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }
        System.out.println("\nTitle                Genre          Status       Rating");
        for (Movie m : movies) System.out.println(m);
    }

    private void deleteMovie() {
        System.out.print("Enter title to delete: ");
        String title = scanner.nextLine().trim();
        boolean ok = movieManager.deleteMovie(title);
        System.out.println(ok ? "✔ Movie deleted." : "✖ Movie not found.");
    }

    private void editMovie() {
        System.out.print("Enter title to edit: ");
        String title = scanner.nextLine().trim();

        System.out.print("New status (Enter to skip): ");
        String status = scanner.nextLine().trim();
        String newStatus = status.isEmpty() ? null : status;

        System.out.print("New rating (Enter to skip): ");
        String r = scanner.nextLine().trim();
        Double newRating = r.isEmpty() ? null : parseDoubleOrNull(r);

        boolean ok = movieManager.editMovie(title, newStatus, newRating);
        System.out.println(ok ? "✔ Movie updated." : "✖ Movie not found.");
    }

    private void filterByGenre() {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        List<Movie> list = movieManager.filterByGenre(genre);
        if (list.isEmpty()) {
            System.out.println("No movies found in this genre.");
            return;
        }
        System.out.println("\nFiltered Movies:");
        for (Movie m : list) System.out.println(m);
    }

    private void sortMoviesByRating() {
        System.out.print("Sort descending? (yes/no): ");
        boolean desc = scanner.nextLine().trim().equalsIgnoreCase("yes");
        movieManager.sortByRating(desc);
        System.out.println("✔ Movies sorted by rating " + (desc ? "descending." : "ascending."));
    }

    // ---------- Statistics ----------
    private void viewStatistics() {
        var s = stats.compute(movieManager.getAllMovies());
        System.out.println("\n=== Statistics ===");
        System.out.printf("Total movies: %d%n", s.total);
        System.out.printf("Watched: %d%n", s.watched);
        System.out.printf("Average rating (rated only): %.2f%n", s.avgRating);
    }

    // ---------- Persistence ----------
    private void saveToDisk(boolean verbose) {
        try {
            storage.save(DATA_PATH, movieManager.getAllMovies());
            if (verbose) System.out.println("✔ Saved to " + DATA_PATH);
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private void loadFromDisk(boolean verbose) {
        try {
            storage.ensureFile(DATA_PATH);
            var loaded = storage.load(DATA_PATH);
            movieManager.getAllMovies().clear();
            movieManager.getAllMovies().addAll(loaded);
            if (verbose) System.out.println("✔ Loaded " + loaded.size() + " movies from " + DATA_PATH);
        } catch (Exception e) {
            if (verbose) System.out.println("Load failed: " + e.getMessage());
        }
    }

    // ---------- utils ----------
    private double readDoubleOrDefault(double defVal) {
        String s = scanner.nextLine().trim();
        Double v = parseDoubleOrNull(s);
        return v == null ? defVal : v;
    }

    private Double parseDoubleOrNull(String s) {
        try { return Double.parseDouble(s); } catch (Exception ignored) { return null; }
    }
    
    private void markMovieAsWatched() {
        System.out.print("Enter title to mark as Watched: ");
        String title = scanner.nextLine().trim();
        boolean ok = movieManager.markAsWatched(title);
        System.out.println(ok ? "✔ Marked as Watched." : "✖ Movie not found.");
    }
    
}


