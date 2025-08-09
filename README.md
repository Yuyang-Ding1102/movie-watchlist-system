# Movie Watchlist & Ratings System

## Project Overview
This is a **Java Command-Line Interface (CLI)** application for managing a personal movie watchlist.  
Users can track movies they want to watch or have already watched, along with optional ratings.  
The system supports adding, editing, deleting, filtering, sorting, viewing statistics, and saving data to CSV for persistence.

This project follows **Object-Oriented Programming (OOP)** principles and is implemented entirely in Java.

---

## Team Members
- **Yuyang Ding** (yd2734)  
- **Yirui Dong** (yd2833)  

---

## Features

| Feature              | Description |
|----------------------|-------------|
| **Add Movie**        | Add a movie with title and genre (required); optional: status (Watched/Not Watched) and rating (0–10) |
| **Edit Movie**       | Update existing movie details such as status or rating |
| **Delete Movie**     | Remove a movie from the watchlist |
| **Mark as Watched**  | Change the status to "Watched" |
| **View All Movies**  | Display all movies in a formatted table on CLI |
| **Filter by Genre**  | View movies of a specific genre |
| **Sort by Rating**   | Sort movies based on rating (ascending or descending) |
| **Statistics**       | Show average rating and number of watched movies |
| **Export to CSV**    | Save data to a local CSV file for persistence |
| **Auto Load on Start** | Automatically loads movies from CSV on program start (extra feature) |

---

## Folder Structure
```
src/
 ├── model/
 │    └── Movie.java
 ├── service/
 │    ├── MovieManager.java
 │    └── StatisticsService.java
 ├── storage/
 │    └── StorageService.java
 └── ui/
      └── CLIHandler.java
data/
 └── movies.csv
stabilize_kit/
```

---

## How to Run

### **1. Clone the repository**
```bash
git clone <your-repo-url>
cd <your-repo-folder>
```

### **2. Initialize the environment**
Run the setup script (creates `.vscode` config, cleans, and compiles):
```bash
./stabilize_kit/setup.sh
```

### **3. Compile**
```bash
./stabilize_kit/compile.sh
```

### **4. Run**
```bash
./stabilize_kit/run.sh
```

---

## CSV Data File
- The application reads/writes movie data from:  
  **`data/movies.csv`**
- CSV format:
  ```
  Title,Genre,Status,Rating
  Interstellar,Sci-Fi,Watched,9.5
  Dune,Sci-Fi,Not Watched,0.0
  ```

---

## UML Diagrams
As per project requirements, the following diagrams will be submitted:
- **Use Case Diagram** (Add, Edit, Delete, Mark as Watched, View, Filter, Sort, View Stats, Export)
- **Class Diagram** (Movie, MovieManager, StatisticsService, StorageService, CLIHandler)
- **Sequence Diagram** (Optional: Add Movie and CSV Save Flow)

---

## Authors
- **Yuyang Ding** – Core logic, CLI interface, storage handling  
- **Yirui Dong** – Feature design, testing, UML documentation  

---
