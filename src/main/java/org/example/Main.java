package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        UserInput userInput = new UserInput();
        int mapSize = userInput.getMapSize();
        int difficulty = userInput.getDifficulty();


        Game game = new Game(mapSize, difficulty);
        game.startGame();
        }
    }
