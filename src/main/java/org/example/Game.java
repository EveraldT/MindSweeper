package org.example;

import java.util.Scanner;

public class Game {
    public Map gameBoard;
    public boolean isGameOver;

    public Game() {
        gameBoard = new Map(6, 6);
        isGameOver = false;
        }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

    //Start game loop
        while (!isGameOver) {
        gameBoard.printBoard();
            System.out.println("Choose row and column(FORMAT: 1,1)");
            String input = scanner.nextLine();

            String[] parts = input.split(" ");
            int row = Integer.parseInt(parts[0]) - 1;
            int column = Integer.parseInt(parts[1]) - 1;

            if (row < 0 || row >= gameBoard.rows || column < 0 || column >= gameBoard.columns ) {
                System.out.println("Invalid input, Enter valid row and column numbers.");
                continue;
            }

            if (gameBoard.board[row][column].isMine) {
                gameBoard.printBoard();
                //change it so it prints out the board with the bomb showing
                System.out.println("GAME OVER!");
                isGameOver = true;
            } else {
                gameBoard.revealTile(row, column);
                System.out.println("Safe Move, Continue");
            }
    }

        scanner.close();

    }




    }










