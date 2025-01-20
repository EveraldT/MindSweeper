package org.example;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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

    public Game(int mapSize, int difficulty){
        this.gameBoard = new Map(mapSize, mapSize, calculateMaxMines(difficulty, mapSize));
        this.userInput = new UserInput();
        this.tileFlagger = new TileFlagger();
        this.isGameOver = false;
        this.isFirstMove = true; //initialise as true
    }

        public int calculateMaxMines(int difficulty, int mapSize) {
        int totalTiles = mapSize * mapSize;

            switch (difficulty) {
                case 1: // Easy
                    return (int) (totalTiles * 0.1); // 10% mines
                case 2: // Intermediate
                    return (int) (totalTiles * 0.15); // 15% mines
                case 3: // Hard
                    return (int) (totalTiles * 0.2); // 20% mines
                default:
                    return (int) (totalTiles * 0.1); // Default to 10% for Easy
            }
            //20% of total tiles set to mines, and capped to min 3 by comparing
            //max mines variable declared using math max function to compare value of 20% of total tiles and another value used as a cap
//            int maxMines = Math.max(3,(int) (totalTiles * 0.2));
//            int testMineAmount = 1;
//            return maxMines;
        }

        public void startGame() {
        Scanner scanner = new Scanner(System.in);
        gameBoard.revealAllTilesForTesting();
        System.out.println("Number of Mines: " + gameBoard.getNumberOfMines());


            //Start game loop
        while (!isGameOver) {
        gameBoard.printBoard();

        GameTimer timer = new GameTimer();
        timer.start();

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
                        SystemMessages.invalidOption();
                        continue;
                    }

                    Tile tile = gameBoard.board[row][column];

                    if (isFirstMove) {
                        if (tile.isMine) {
                            SystemMessages.relocateMine();
                            gameBoard.relocateMine(row, column); // Remove and relocate the mine
                            gameBoard.calculateAdjacentMines(); // recalculate the adjacent mines after relocation
                        }
                        isFirstMove = false; // Mark the first move as completed
//                        System.out.println("TEST this is the new board:");
//                        gameBoard.revealAllTilesForTesting();
//                        SystemMessages.numberOfMines(mineCount);

                    }

                    if (tile.isFlagged) {
                        SystemMessages.flagTileReveal();
                        continue;
                    }

                    if (tile.isRevealed) {
                        SystemMessages.alreadyRevealed();
                        continue;
                    }


                    if (tile.isMine) {
                        gameBoard.printFullBoard();
                        SystemMessages.gameOver();
                        timer.stop();
                        isGameOver = true;
                    } else {
                        gameBoard.revealTiles(row, column);
                        int revealedTileCount = gameBoard.revealedTilesCount();
                        SystemMessages.safeMove();
                        SystemMessages.numberOfMines(mineCount);
                        SystemMessages.revealedTilesAmount(revealedTileCount);

                    }
                    break;

                case 2://flag/unflag tile
                    int[] flagPosition = userInput.getTilePosition();
                    int flagRow = flagPosition[0];
                    int flagCol = flagPosition[1];

                    if (flagRow < 0 || flagRow >= gameBoard.rows || flagCol < 0 || flagCol >= gameBoard.columns) {
                        SystemMessages.invalidOption();
                        continue;
                    }

                    tileFlagger.toggleFlag(gameBoard, flagRow, flagCol);
                    break;

                case 3://quit game
                    System.out.println("Thanks for Playing!");
                    isGameOver = true;
                    break;

                case 4:
                    timer.checkTimer();
                    break;

                default:
                    SystemMessages.invalidOption();
                    break;

            }

            int totalTiles = gameBoard.rows * gameBoard.columns;
            int totalSafeTiles = totalTiles - mineCount;

            //Win condition, get total safe tiles by calculating all tiles (by getting row*col) and substracting the mine count from it
            //and if the revealed tiles count is equal to the amount of total safe tiles that means the player has revealed all safe tiles and wins
            if (gameBoard.revealedTilesCount() == totalSafeTiles) {
                SystemMessages.winGame();
                gameBoard.revealAllTilesForTesting();
                isGameOver = true;
            }

    }

    }

    public void timerStart(){
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long elaspedTime = (System.currentTimeMillis() - startTime)/1000;
                System.out.println("Elasped Time: " + elaspedTime);
            }


        };
        timer.scheduleAtFixedRate(task, 1, 1000);
        System.out.println(task);

    }

    public void checkTimer(Timer time) {

    }




    }










