package org.example;

import java.util.Scanner;

public class Game {
    public Map gameBoard;
    public boolean isGameOver;
    private boolean isFirstMove;
    private UserInput userInput;
    private TileFlagger tileFlagger;

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

//    public Game() {
//        int numberOfMines = calculateMaxMines(ROWS, COLUMNS);
//        gameBoard = new Map(ROWS, COLUMNS, numberOfMines );
//        isGameOver = false;
//    }

    public Game(int rows, int columns){
        this.gameBoard = new Map(rows, columns, calculateMaxMines(rows, columns));
        this.userInput = new UserInput();
        this.tileFlagger = new TileFlagger();
        this.isGameOver = false;
        this.isFirstMove = true; //initialise as true
    }

//    public void buildGame(int totalMines) {
//        gameBoard.placeRandomMines(totalMines);
//        gameBoard.calculateAdjacentMines();
//    }

        public int calculateMaxMines(int rows, int columns) {
        int totalTiles = rows * columns;
            //20% of total tiles set to mines, and capped to min 3 by comparing
            //max mines variable declared using math max function to compare value of 20% of total tiles and another value used as a cap
            int maxMines = Math.max(3,(int) (totalTiles * 0.2));
            int testMineAmount = 1;
            return maxMines;
        }


        public void startGame() {
        Scanner scanner = new Scanner(System.in);
        gameBoard.revealAllTilesForTesting();
            System.out.println("Number of Mines: " + gameBoard.getNumberOfMines());


            //Start game loop
        while (!isGameOver) {
        gameBoard.printBoard();
//            System.out.println("Choose row and column(FORMAT: 1,1)");
//            String input = scanner.nextLine();
//            instead of asking the user directly and taking in the input, the userinput class handles main menu
            int choice = userInput.getUserOption();
            int mineCount = gameBoard.getNumberOfMines();


            switch (choice) {
                case 1://reveal tile
                    int[] position = userInput.getTilePosition();
                    int row = position[0];
                    int column = position [1];

                    if (row < 0 || row >= gameBoard.rows || column < 0 || column >= gameBoard.columns) {
                        System.out.println("Invalid position. Try again.");
                        continue;
                    }

                    Tile tile = gameBoard.board[row][column];

                    if (isFirstMove) {
                        if (tile.isMine) {
                            System.out.println("MINE HIT ON FIRST TURN RELOCATING MINE POSITION ");
                            gameBoard.relocateMine(row, column); // Remove and relocate the mine
                            gameBoard.calculateAdjacentMines(); // recalculate the adjacent mines after relocation
                        }
                        isFirstMove = false; // Mark the first move as completed
                        System.out.println("TEST this is the new board:");
                        gameBoard.revealAllTilesForTesting();
                        System.out.println("Number of Mines: " + mineCount);

                    }


                    if (tile.isMine) {
                        gameBoard.printFullBoard();
                        System.out.println("GAME OVER");
                        isGameOver = true;
                    } else {
                        gameBoard.revealTile(row, column);
                        System.out.println("SAFE MOVE, continue");
                        System.out.println("Number of Mines: " + mineCount);
                        System.out.println("Number of revealed tiles: " + gameBoard.revealedTilesCount());

                    }
                    break;

                case 2://flag/unflag tile
                    int[] flagPosition = userInput.getTilePosition();
                    int flagRow = flagPosition[0];
                    int flagCol = flagPosition[1];

                    if (flagRow < 0 || flagRow >= gameBoard.rows || flagCol < 0 || flagCol >= gameBoard.columns) {
                        System.out.println("Invalid position. Try again.");
                        continue;
                    }

                    tileFlagger.toggleFlag(gameBoard, flagRow, flagCol);
                    break;

                case 3://quit game
                    System.out.println("Thanks for Playing!");
                    isGameOver = true;
                    break;

                default:
                    System.out.println("Invalid Option. Try Again");
                    break;

            }

            int totalTiles = gameBoard.rows * gameBoard.columns;
            int totalSafeTiles = totalTiles - mineCount;

            //Win condition, get total safe tiles by calculating all tiles (by getting row*col) and substracting the mine count from it
            //and if the revealed tiles count is equal to the amount of total safe tiles that means the player has revealed all safe tiles and wins
            if (gameBoard.revealedTilesCount() == totalSafeTiles) {
                System.out.println("YOU WIN! ALL MINES AVOIDED");
                gameBoard.revealAllTilesForTesting();
                isGameOver = true;
            }


//            String[] parts = input.split(" ");
//            int row = Integer.parseInt(parts[0]) - 1;
//            int column = Integer.parseInt(parts[1]) - 1;
//
//            if (row < 0 || row >= gameBoard.rows || column < 0 || column >= gameBoard.columns ) {
//                System.out.println("Invalid input, Enter valid row and column numbers.");
//                continue;
//            }
//
//            if (gameBoard.board[row][column].isMine) {
//                gameBoard.printBoard();
//                //change it so it prints out the board with the bomb showing
//                System.out.println("GAME OVER!");
//                isGameOver = true;
//            } else {
//                gameBoard.revealTile(row, column);
//                System.out.println("Safe Move, Continue");
//            }
    }

//        scanner.close();

    }




    }










