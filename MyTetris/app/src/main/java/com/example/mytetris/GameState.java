package com.example.mytetris;

public class GameState extends Thread {
    private static TetrisUI ui;
    private static GameRun R;
    private static ScoreBoard point;
    static int State = 1;

    public GameState(MainActivity Run) {
        ui = Run.UI;
        R = Run.run;
        point = Run.SB;
    }

    public static boolean GameRun() {
        if (State == 1) {
            State = 2;
            R.start();
            return true;
        } else {
            if (State == 4) {
                State = 2;
                point.setScore(0);
                point.update(0);
                R.initPlace();
                ui.setLayout(R.getPlace());
                R.start();
            }

            return false;
        }
    }

    public static boolean GamePause() {
        try {
            if (State == 2) {
                State = 3;
                System.out.println("Pause: " + State);
                R.suspend();
                return true;
            } else if (State == 3) {
                State = 2;
                System.out.println("Play: " + State);
                R.resume();
                return true;
            } else {
                GameRun();
                return false;
            }
        } catch (Exception var1) {
            var1.printStackTrace();
            return false;
        }
    }

    public static boolean GameEnd() {
        if (State != 4) {
            State = 4;
            ui.setText("Game End \n Score: " + point.getScore());
            R.stop();
            return true;
        } else {
            return false;
        }
    }


}
