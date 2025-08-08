package service;

import java.util.List;
import model.Movie;

public class StatisticsService {

    public static class Stats {
        public final int total;
        public final int watched;
        public final double avgRating;

        public Stats(int total, int watched, double avgRating) {
            this.total = total;
            this.watched = watched;
            this.avgRating = avgRating;
        }

        @Override
        public String toString() {
            return String.format(
                "Total: %d, Watched: %d, Avg Rating (rated only): %.2f",
                total, watched, avgRating
            );
        }
    }

    public Stats compute(List<Movie> movies) {
        int total = movies.size();
        int watched = 0;
        double sum = 0.0;
        int ratedCount = 0;

        for (Movie m : movies) {
            if ("Watched".equalsIgnoreCase(m.getStatus())) {
                watched++;
            }
            double r = m.getRating();
            if (!Double.isNaN(r)) {
                ratedCount++;
                sum += r;
            }
        }

        double avg = ratedCount == 0 ? 0.0 : (sum / ratedCount);
        return new Stats(total, watched, avg);
    }
}

