package org.example;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer {
    private Timer timer;
    private int elapsedSeconds;

    //constructor
    public GameTimer() {
        this.timer = new Timer();
        this.elapsedSeconds = 0;
    }

    //time logic cooked
    public void start(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                elapsedSeconds++;
               // System.out.println("Elasped Time: " + elapsedSeconds + "seconds");
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

    }

    public void stop(){
        timer.cancel();
        System.out.println("Final Time: " + elapsedSeconds + " Seconds");
    }

    public void checkTimer() {
        System.out.println("Current Time: " + getElapsedSeconds() + " Seconds");
    }

    public int getElapsedSeconds(){
        return elapsedSeconds;
    }

}
