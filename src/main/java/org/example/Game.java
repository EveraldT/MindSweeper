package org.example;

import java.util.Scanner;

public class Game {
    public Map gameBoard;
    public boolean isGameOver;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    public Game() {
        int numberOfMines = calculateMaxMines(ROWS, COLUMNS);
        gameBoard = new Map(ROWS, COLUMNS, numberOfMines );
        isGameOver = false;
    }

    public void buildGame(int totalMines) {
        gameBoard.placeRandomMines(totalMines);
        gameBoard.calculateAdjacentMines();
    }

        public int calculateMaxMines(int rows, int columns) {
        int totalTiles = rows * columns;
            //20% of total tiles set to mines, and capped to min 3 by comparing
            //max mines variable declared using math max function to compare value of 20% of total tiles and another value used as a cap
            return Math.max(3,(int) (totalTiles * 0.2));
        }


        public void startGame() {
        Scanner scanner = new Scanner(System.in);
        gameBoard.revealAllTilesForTesting();

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










