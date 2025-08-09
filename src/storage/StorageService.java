package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.Movie;


public class StorageService {
    private static final String HEADER = "title,genre,status,rating";

    /** Save movies to CSV (overwrite). Creates parent directories if needed. */
    public void save(String filePath, List<Movie> movies) throws IOException {
        Path p = Path.of(filePath);
        if (p.getParent() != null) {
            Files.createDirectories(p.getParent());
        }
        try (BufferedWriter w = Files.newBufferedWriter(p)) {
            w.write(HEADER);
            w.newLine();
            for (Movie m : movies) {
                w.write(toCsvRow(m));
                w.newLine();
            }
        }
    }

    /** Load movies from CSV. If file doesn't exist, returns empty list. */
    public List<Movie> load(String filePath) throws IOException {
        Path p = Path.of(filePath);
        List<Movie> list = new ArrayList<>();
        if (!Files.exists(p)) return list;

        try (BufferedReader r = Files.newBufferedReader(p)) {
            String line;
            boolean first = true;
            while ((line = r.readLine()) != null) {
                if (first) { // skip header if present
                    first = false;
                    if (line.trim().equalsIgnoreCase(HEADER)) continue;
                }
                List<String> cols = parseCsvLine(line);
                if (cols.size() < 4) continue;
                String title = cols.get(0);
                String genre = cols.get(1);
                String status = cols.get(2).isEmpty() ? "Not Watched" : cols.get(2);
                double rating = 0.0;
                try { rating = Double.parseDouble(cols.get(3)); } catch (NumberFormatException ignored) {}
                list.add(new Movie(title, genre, status, rating));
            }
        }
        return list;
    }

    /** Ensure file exists with header (optional helper). */
    public void ensureFile(String filePath) throws IOException {
        Path p = Path.of(filePath);
        if (!Files.exists(p)) {
            if (p.getParent() != null) Files.createDirectories(p.getParent());
            try (BufferedWriter w = Files.newBufferedWriter(p)) {
                w.write(HEADER);
                w.newLine();
            }
        }
    }

    // ----- helpers -----

    private String toCsvRow(Movie m) {
        return String.join(",",
                quote(m.getTitle()),
                quote(m.getGenre()),
                quote(m.getStatus()),
                String.valueOf(m.getRating())
        );
    }

    private String quote(String s) {
        if (s == null) return "\"\"";
        String t = s.replace("\"", "\"\"");
        return "\"" + t + "\""; // always quote; handles commas safely
        }

    /** Simple CSV parser that respects quotes and escaped quotes (""). */
    private List<String> parseCsvLine(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '\"') {
                    // lookahead for escaped quote
                    if (i + 1 < line.length() && line.charAt(i + 1) == '\"') {
                        cur.append('\"');
                        i++; // skip second quote
                    } else {
                        inQuotes = false;
                    }
                } else {
                    cur.append(c);
                }
            } else {
                if (c == '\"') {
                    inQuotes = true;
                } else if (c == ',') {
                    out.add(cur.toString());
                    cur.setLength(0);
                } else {
                    cur.append(c);
                }
            }
        }
        out.add(cur.toString());
        return out;
    }
}

