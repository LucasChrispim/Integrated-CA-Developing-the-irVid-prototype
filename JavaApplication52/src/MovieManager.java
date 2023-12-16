/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gusta
 */
public class MovieManager {
    private Map<String, Movie> movies;
    private static Map<String, Date> rentedMoviesLog; // Keep track of information about the rented movies
    private FileManager fileManager = new FileManager();

    // Constructor to initialize MovieManager attributes
    public MovieManager() {
        this.movies = new HashMap<>();
        this.rentedMoviesLog = new HashMap<>();
    }

    public Map<String, Movie> getMovies(){
        return movies;
    }

    public Map<String, Date> getRentedMoviesLog(){
        return rentedMoviesLog;
         }

    // Method to load movies from a CSV file
    public void loadMoviesFromCSV(String filename) {
        // Read lines from the file
        List<String> lines = fileManager.readLines(filename);

        for (String line : lines) {
            // Split the line into parts
            String[] parts = line.split(",");
            String title = parts[0];
            double price = Double.parseDouble(parts[1]);
            // Add the movie to the map
            movies.put(title, new Movie(title, price));
        }
    }
// Method to display the list of available movies
    public void displayMovieList() {
        System.out.println("\n--- List of Available Movies ---");
        System.out.printf("%-30s %-10s\n", "Movie Title", "Price");

        List<Movie> availableMovies = new ArrayList<>(movies.values());
        // Remove unavailable movies
        availableMovies.removeIf(movie -> !movie.isAvailable());

        // Sort the list of movies by title
        Collections.sort(availableMovies, Comparator.comparing(Movie::getTitle));

        // Display the available movies
        for (Movie movie : availableMovies) {
            System.out.printf("%-30s $%.2f\n", movie.getTitle(), movie.getPrice());
        }
    }
