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
