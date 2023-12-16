/**
 *
 * @author lucas
 */
package UserInteraction;

import com.EirVid.movierent.MenuManager;
import com.EirVid.movierent.MovieManager;
import com.EirVid.movierent.User;
import com.EirVid.movierent.UserManager;
import java.io.IOException;

import java.util.Scanner;

public class UserInteraction {
    private static Scanner scanner = new Scanner(System.in);

    public static void interactWithLoggedInUser(User user, MovieManager movieManager, UserManager userManager) {
        System.out.println("\nWelcome, " + user.getEmail() + "!");
        while (true) {
            System.out.println("\n--- User Options ---");
            System.out.println("1. Rent a Movie");
            System.out.println("2. Display Rented Movies");
            System.out.println("3. Display Historic of Rented Movies");
            System.out.println("4. Display Available Movies");
            System.out.println("5. Total Amount to Pay");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer

            switch (choice) {
                case 1 -> {
                    movieManager.displayMovieList();
                    System.out.print("\nEnter the title of the movie you want to rent: ");
                    String movieTitle = scanner.nextLine();
                    movieManager.rentMovie(user, movieTitle);
                }
                case 2 -> userManager.displayRentedMovies(user);
                case 3 -> userManager.displayHistoricRentedMovies(user);
                case 4 -> movieManager.displayMovieList();
                case 5 -> userManager.displayChargedAmount(user);
                case 0 -> {
                    if (user != null) {
                        System.out.println("Logging out user: " + user.getEmail());
                        user = null; // Log out the user upon exiting
                    }                    
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            
            MenuManager.waitForUser();
        }
    } 
    
}
