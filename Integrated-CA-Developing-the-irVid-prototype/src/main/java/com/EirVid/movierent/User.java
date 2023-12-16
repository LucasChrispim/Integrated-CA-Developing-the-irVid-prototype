/**
 *
 * @author lucas
 */
package com.EirVid.movierent;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String password;
    private List<Movie> rentedMoviesHistoric;

    private List<Movie> rentedMovies;
    private double chargedAmount;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.rentedMovies = new ArrayList<>();
        this.rentedMoviesHistoric = new ArrayList<>();
        this.chargedAmount = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Movie> getRentedMovies() {
        return rentedMovies;
    }

    public double getChargedAmount() {
        return chargedAmount;
    }

    public void increaseChargedAmount(double chargedAmount) {
        this.chargedAmount += chargedAmount;
    }
    
    public void addMovie(Movie movie){
        this.rentedMovies.add(movie);
    }

    public void removeRentedMovie(Movie movie) {
        this.rentedMovies.remove(movie);
    }
    
    public List<Movie> getRentedMoviesHistoric() {
        return rentedMoviesHistoric;
    }
    
    public void addMovieHistoric(Movie movie){
        this.rentedMoviesHistoric.add(movie);
    }
}

