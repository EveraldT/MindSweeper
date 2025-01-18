package org.example;

public class TileFlagger {
    public void toggleFlag(Map gameBoard, int row, int col){
        Tile tile = gameBoard.board[row][col];
        if (!tile.isRevealed) {
            tile.isFlagged = !tile.isFlagged;
            //ternary operator to determine flag value
            System.out.println(tile.isFlagged ? "Flagged" : "Unflagged");
        } else {
            System.out.println("Cannot flag a revealed tile!");

        }
    }
}
