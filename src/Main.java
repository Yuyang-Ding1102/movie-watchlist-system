import model.Movie;
import service.MovieManager;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        manager.addMovie(new Movie("Inception", "Sci-Fi", "Watched", 9.0));
        manager.addMovie(new Movie("Interstellar", "Sci-Fi", "Not Watched", 8.5));

        for (Movie m : manager.getAllMovies()) {
            System.out.println(m);
        }
    }
}
