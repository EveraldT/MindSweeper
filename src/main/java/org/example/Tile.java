package org.example;

public class Tile {
    boolean isRevealed;
    boolean isMine;
    public int adjacentMines;

    //Constructor
    public Tile() {
        this.isMine = isMine;
        this.isRevealed = false;
    }
    //method for changing tile to revealed
    public void revealTile() {
        this.isRevealed = true;
    }

    //Getter for revealed tile
    public boolean isRevealed() {
        return this.isRevealed;
    }

    public boolean isMine(){
        return this.isMine;
    }

}
