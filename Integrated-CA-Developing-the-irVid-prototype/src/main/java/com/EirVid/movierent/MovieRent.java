/**
 *@author Lucas
 *@author Gustavo
 *@author Leandro
 */
package com.EirVid.movierent;

import UserInteraction.UserInteraction;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.EirVid.movierent.MenuManager;

public class MovieRent {
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser;
    private static MovieManager movieManager;
    private static UserManager userManager;
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        // Initialize menuManager, movieManager and userManager instances
        movieManager = new MovieManager();
        userManager = new UserManager();
        
        // Load movies from CSV file
        movieManager.loadMoviesFromCSV("resources/movies.csv");

        // Schedule a task to check and restore the availability of movies every 5 seconds
        scheduler.scheduleAtFixedRate(() -> movieManager.checkAndRestoreAvailability(userManager.getUsers()),
                1, 5, TimeUnit.SECONDS);

        while (true) {
            System.out.println("\n--- Movie Rental App ---");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Display Top Rented Movies (last 5 minutes)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    // Check if the email format is valid
                    if (isValidEmail(email)) {
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        // Create a new user
                        userManager.createUser(email, password);
                    } else {
                        System.out.println("Invalid email format. Please try again.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    // Log in the user and start user interaction
                    loggedInUser = userManager.login(email, password);
                    if (loggedInUser != null) {
                        UserInteraction.interactWithLoggedInUser(loggedInUser, movieManager, userManager);
                    }
                }
                case 3 -> movieManager.displayTopMovies();
                case 0 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            MenuManager.waitForUser();
        }     
    }

    // Helper method to check if the email format is valid
    private static boolean isValidEmail(String email) {
        // Simple regular expression to check basic email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}