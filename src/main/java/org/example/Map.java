package org.example;

import java.util.Random;

public class Map {
    Tile[][] board;
    int rows;
    int columns;

    public Map (int rows, int columns, int numberOfMines) {
        this.rows = rows;
        this.columns = columns;
        this.board = new Tile[rows][columns];

        // Initialize all tiles as regular (non-mine)
        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < columns; j++ ){
                board[i][j] = new Tile();
            }
        }

        placeRandomMines(numberOfMines);
        calculateAdjacentMines();

    }

    //method using random function to fill the board randomly using number of mines parameter in the map object
    //starts placed mines as 0 so for the loop is executed until the number of placed mines is equal to the number of mines
    //if statement included in the loop to prevent mines from being
    public void placeRandomMines(int numberOfMines) {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < numberOfMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(columns);

            //if the tile is not already a mine
            if (!board[row][col].isMine) {
                board[row][col].isMine = true;
                placedMines++;
            }
        }

    }

    //REQUIRES REVIEW AND READ OVER NOT FINAL METHOD!
    public void calculateAdjacentMines() {
        // Loop through all tiles on the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].isMine) {
                    // If the tile is a mine, we don't need to calculate anything for it
                    continue;
                }
                // Check surrounding tiles for mines
                int mineCount = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int newRow = i + x;
                        int newCol = j + y;

                        // Skip out-of-bounds and the current tile itself
                        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= columns || (x == 0 && y == 0)) {
                            continue;
                        }
                        // if the neighbour is a mine, increment minecount
                            if (board[newRow][newCol].isMine) {
                                mineCount++;
                            }

                    }
                }
                board[i][j].adjacentMines = mineCount;  // Set the number of adjacent mines for the tile
            }
        }
    }

    //now recursive REQUIRES REVIEW AND READ OVER NOT FINAL METHOD!
    public void revealTile(int row, int col) {

        // base case end the recursion if is out of bounds
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            return;
        }

        // base case end the recursion if the tile is already revealed or a mine
        Tile tile = board[row][col];
        if (tile.isRevealed || tile.isMine) {
            return;
        }
        // Reveal the current tile
        tile.revealTile();

        // base case if the tile has adjacent mines, stop revealing further
        if (tile.adjacentMines > 0) {
            return;
        }

        // Reveal surrounding tiles (iterate over all neighbors)
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue; // Skip the current tile itself
                }
                int newRow = row + x;
                int newCol = col + y;
                revealTile(newRow, newCol); // Recursively reveal neighboring tiles
            }
        }


    }

    public void printBoardold(){
        for(int i = 0; i < rows; i++ ){
            for(int j = 0; j < columns; j++){
                Tile currentTile = board[i][j];
                String tileState;
                if (currentTile.isRevealed){
                    if (currentTile.isMine) {
                        tileState = "M";
                    } else {
                        tileState = "0";
                    }
                } else {
                    tileState = "#";
                }

                switch (tileState) {
                        case "M":
                            System.out.print("M ");
                            break;
                        case "0":
                            System.out.print("0 ");
                            break;
                    case "#":
                        System.out.print("# ");
                        break;
                    default:
                        System.out.print("?");

                }
            }
            //Line Break
            System.out.println();
        }

    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Tile currentTile = board[i][j];
                String display;
                if (currentTile.isRevealed) {
                    if (currentTile.isMine) {
                        display = "M";
                    } else {
                        display = Integer.toString(currentTile.adjacentMines);
                    }
                } else {
                    display = "#"; // Unrevealed tiles
                }
                System.out.print(display + " ");
            }
            System.out.println();
        }
    }

    //test method to see the unrevealed board before game starts
    public void printFullBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].isMine) {
                    System.out.print("M ");  // M represents a mine
                } else {
                    System.out.print("0 ");  // 0 represents a non-mine cell
                }
            }
            System.out.println();
        }
    }

    public void revealAllTilesForTesting() {
        // Store the current state of each tile
        boolean[][] previousStates = new boolean[rows][columns];

        // Reveal all tiles and store their current revealed state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                previousStates[i][j] = board[i][j].isRevealed(); // Store current state
                board[i][j].revealTile();                        // Temporarily reveal the tile
            }
        }
        // Print the board with all tiles revealed
        printBoard();
        // Restore the previous state of each tile
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!previousStates[i][j]) {
                    board[i][j].isRevealed = false; // Hide the tile again if it wasn't already revealed
                }
            }
        }
    }




}
