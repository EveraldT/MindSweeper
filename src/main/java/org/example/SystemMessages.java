package org.example;

public final class SystemMessages {

    public static void alreadyRevealed() {
        System.out.println("Tile already revealed choose another!");
    }

    public static void flagTileReveal() {
        System.out.println("Cannot reveal a flagged tile, unflag it to reveal!");
    }

    public static void numberOfMines(int mineCount){
        System.out.println("Number of Mines: " + mineCount);
    }

    public static void invalidOption(){
        System.out.println("Invalid Option. Try Again");
    }

    public static void winGame(){
        System.out.println("YOU WIN! ALL MINES AVOIDED");
    }

    public static void gameOver() {
        System.out.println("GAME OVER");
    }

    public static void revealedTilesAmount(int count) {
        System.out.println("Number of revealed tiles: " + count);
    }

    public static void safeMove(){
        System.out.println("SAFE MOVE, continue");
    }

    public static void relocateMine(){
        System.out.println("MINE HIT ON FIRST TURN RELOCATING MINE POSITION ");
    }

}
