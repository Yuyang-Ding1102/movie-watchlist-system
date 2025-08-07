package ui;

import model.Movie;
import service.MovieManager;

import java.util.List;
import java.util.Scanner;

public class CLIHandler {
    private MovieManager movieManager;
    private Scanner scanner;

    public CLIHandler() {
        this.movieManager = new MovieManager();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    viewAllMovies();
                    break;
                case "3":
                    deleteMovie();
                    break;
                case "4":
                    editMovie();
                    break;
                case "5":
                    filterByGenre();
                    break;
                case "6":
                    sortMoviesByRating();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
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
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void addMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Status (Watched/Not Watched): ");
        String status = scanner.nextLine();
        System.out.print("Rating (0.0 - 10.0): ");
        double rating = Double.parseDouble(scanner.nextLine());
        movieManager.addMovie(new Movie(title, genre, status, rating));
        System.out.println("Movie added.");
    }

    private void viewAllMovies() {
        List<Movie> movies = movieManager.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            System.out.println("\nTitle                Genre          Status       Rating");
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    private void deleteMovie() {
        System.out.print("Enter title to delete: ");
        String title = scanner.nextLine();
        boolean success = movieManager.deleteMovie(title);
        System.out.println(success ? "Movie deleted." : "Movie not found.");
    }

    private void editMovie() {
        System.out.print("Enter title to edit: ");
        String title = scanner.nextLine();
        System.out.print("New status (or press Enter to skip): ");
        String status = scanner.nextLine();
        System.out.print("New rating (or press Enter to skip): ");
        String ratingInput = scanner.nextLine();
        String newStatus = status.isEmpty() ? null : status;
        Double newRating = ratingInput.isEmpty() ? null : Double.parseDouble(ratingInput);
        boolean success = movieManager.editMovie(title, newStatus, newRating);
        System.out.println(success ? "Movie updated." : "Movie not found.");
    }

    private void filterByGenre() {
        System.out.print("Enter genre to filter: ");
        String genre = scanner.nextLine();
        List<Movie> filtered = movieManager.filterByGenre(genre);
        if (filtered.isEmpty()) {
            System.out.println("No movies found in this genre.");
        } else {
            System.out.println("\nFiltered Movies:");
            for (Movie m : filtered) {
                System.out.println(m);
            }
        }
    }

    private void sortMoviesByRating() {
        System.out.print("Sort descending? (yes/no): ");
        String input = scanner.nextLine();
        boolean descending = input.equalsIgnoreCase("yes");
        movieManager.sortByRating(descending);
        System.out.println("Movies sorted by rating " + (descending ? "descending." : "ascending."));
    }
}
