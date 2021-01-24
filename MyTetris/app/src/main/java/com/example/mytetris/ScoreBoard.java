package com.example.mytetris;

public class ScoreBoard {
    private TetrisUI ui;
    private int curScore;
    public ScoreBoard(TetrisUI UI) {
        this.ui = UI;
        this.curScore = 0;
        ui.setScore(curScore);
    }
    public void update(int getPoint){
        curScore += getPoint;
        ui.setScore(curScore);
        speedUp();
    }
    public void setScore(int score){
        curScore = score;
    }
    public int getScore(){
        return curScore;
    }
    private void speedUp(){
        if(curScore >= 10000)
            GameRun.dropdelay = 100;
        else if (curScore >= 9000)
            GameRun.dropdelay = 200;
        else if(curScore >= 7000)
            GameRun.dropdelay = 350;
        else if(curScore >= 5000)
            GameRun.dropdelay = 500;
        else if(curScore >= 3000)
            GameRun.dropdelay = 650;
        else if(curScore >= 1000)
            GameRun.dropdelay = 800;
        else
            GameRun.dropdelay = 1000;
        /*if(curScore >= 10000)
            BlockDrop.dropdelay = 100;
        else if (curScore >= 9000)
            BlockDrop.dropdelay = 200;
        else if(curScore >= 7000)
            BlockDrop.dropdelay = 350;
        else if(curScore >= 5000)
            BlockDrop.dropdelay = 500;
        else if(curScore >= 3000)
            BlockDrop.dropdelay = 650;
        else if(curScore >= 1000)
            BlockDrop.dropdelay = 800;
        else
            BlockDrop.dropdelay = 1000;*/
    }
}
