package org.example;

import java.util.Random;

public class Map {
    Tile[][] board;
    int rows;
    int columns;


    public Map (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new Tile[rows][columns];
        this.board[0][0] = new Tile(true);

        //fill rest of the tiles with non mine tiles
        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < columns; j++ ){
                if (board[i][j] == null) {
                    board[i][j] = new Tile(false);
                }
            }
        }
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



}
