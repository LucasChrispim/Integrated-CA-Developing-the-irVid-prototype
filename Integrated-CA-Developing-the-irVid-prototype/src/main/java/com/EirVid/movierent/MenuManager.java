
/**
 *@author Lucas
 *@author Gustavo
 *@author Leandro
 */
package com.EirVid.movierent;

import java.io.IOException;
import java.util.Scanner;

public class MenuManager {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public MenuManager() {
        
    }
    
    public final static void waitForUser(){
        System.out.println("\n\nPress Enter to continue...");
        scanner.nextLine();  // Wait for the user to restart the menu
    }
    
    
}
