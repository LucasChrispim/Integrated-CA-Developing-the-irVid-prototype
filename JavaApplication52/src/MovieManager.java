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

