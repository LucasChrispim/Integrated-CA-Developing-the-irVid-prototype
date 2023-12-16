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
     // Method to check and restore the availability of movies
    public void checkAndRestoreAvailability(Map<String, User> users) {
        // Check and restore the availability of movies
        Date now = new Date();
        for (Movie movie : movies.values()) {
            if (!movie.isAvailable() && (now.getTime() - movie.getRentedTime().getTime()) >= TimeUnit.MINUTES.toMillis(1)) {
                movie.setAvailable(true);

                // Remove the movie from the rented movies list of all users
                for (User user : users.values()) {
                    if (user.getRentedMovies().contains(movie)) {
                        user.removeRentedMovie(movie);
                    }
                }
            }
        }
    }
    // Method to rent a movie
    public void rentMovie(User user, String movieTitle) {
        Movie movie = movies.get(movieTitle);
        if (movie != null && movie.isAvailable()) {
            user.addMovie(movie);
            user.addMovieHistoric(movie);
            movie.setAvailable(false);  // Mark as not available
            movie.setRentedTime(new Date());  // Record the rental time

            // Add the movie's price to the user's charged amount
            user.increaseChargedAmount(movie.getPrice());

            rentedMoviesLog.put(movie.getTitle(), movie.getRentedTime());

            System.out.println("Movie rented successfully!");
        } else {
            System.out.println("Movie not found or already rented. Please check the title and try again.");
        }
    }

    // Static method to display the top 5 rented movies of the last 5 minutes
    public static void displayTopMovies() {
        Date now = new Date();

        List<String> last5MinutesLog = rentedMoviesLog.entrySet().stream()
                .filter(entry -> {
                    Date rentedTime = entry.getValue();
                    // Check if the record is less than 5 minutes old
                    return now.getTime() - rentedTime.getTime() < TimeUnit.MINUTES.toMillis(5);
                })
                .map(entry -> String.format("Movie: %s", entry.getKey()))
                .collect(Collectors.toList());

        System.out.println("Top 5 movies rented in the last 5 minutes:");
        last5MinutesLog.forEach(System.out::println);
    }

    // Add other relevant methods for movie management
}

