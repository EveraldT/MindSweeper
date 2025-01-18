package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    //display main menu
    public int getUserOption() {
        System.out.println("Choose an option: ");
        System.out.println("1. Reveal a tile");
        System.out.println("2. Flag/UnFlag a tile");
        System.out.println("3. Quit the game");

        int choice;

        //loop to make sure the user input for choice is between 1 and 3
        while (true) {
            try{
                System.out.println("Enter your choice (1-3): ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }
        }
        return choice;
    }

    public int [] getTilePosition() {
        System.out.println("Enter the row and column of the tile you want to select (e.g., 1 1):");
        while (true) {
            try {
                String input = scanner.nextLine();
                return parseInput(input);
                } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid format. Please enter two numbers separated by a space.");
            }

        }
    }


//    public int [] parseInput(String input) {
//        String[] parts = input.split(" ");
//        int row = Integer.parseInt(parts[0]) - 1;
//        int column = Integer.parseInt(parts[1]) - 1;
//        return new int [] {row, column};
//    }

    //review
    public int[] parseInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Input must contain exactly two numbers.");
        }
        int row = Integer.parseInt(parts[0]) - 1;
        int column = Integer.parseInt(parts[1]) - 1;
        return new int[]{row, column};
    }

    public String getUserInputPosition() {
        System.out.println("Choose row and column(FORMAT: 1,1)");
        return scanner.nextLine();
    }


    public void close() {
        scanner.close();
    }


}
