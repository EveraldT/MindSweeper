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
//        this.board[0][0] = new Tile(true);

        // Initialize all tiles as regular (non-mine)
        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < columns; j++ ){
                board[i][j] = new Tile();
            }
        }

        placeRandomMines(numberOfMines);

    }

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

    public void revealTile(int rows, int columns) {
        Tile tile = board[rows][columns];
        tile.revealTile();
    }

    public void printBoard(){
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
                            System.out.print("M");
                            break;
                        case "0":
                            System.out.print("0");
                            break;
                    case "#":
                        System.out.print("#");
                        break;
                    default:
                        System.out.print("?");

                }
            }
            //Line Break
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




}
