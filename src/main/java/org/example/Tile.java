package org.example;

public class Tile {
    boolean isRevealed;
    boolean isMine;
    boolean isFlagged;
    public int adjacentMines;


    //Constructor
    public Tile() {
        this.isMine = isMine;
        this.isRevealed = false;
        this.isFlagged = false;
    }
    //method for changing tile to revealed
    public void revealTile() {
        this.isRevealed = true;
    }

    public void flagTile(){
        this.isFlagged = true;
    }

    //Getter for revealed tile
    public boolean isRevealed() {
        return this.isRevealed;
    }

    public boolean isMine(){
        return this.isMine;
    }

    public boolean isFlagged(){
        return this.isFlagged;
    }

}
