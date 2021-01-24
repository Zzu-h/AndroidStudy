package com.example.mytetris;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GridLayout GridLayout_screen;
    private LinearLayout LinearLayout_main;
    int x_size = 10, y_size = 22;

    TetrisUI UI;
    GameRun run;
    ScoreBoard SB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout_main = findViewById(R.id.LinearLayout_main);
        //GridLayout_screen = findViewById(R.id.GridLayout_screen);

        this.UI = new TetrisUI(MainActivity.this,LinearLayout_main);
        this.SB = new ScoreBoard(this.UI);
        this.run = new GameRun(this.UI, this.SB);
        new GameState(this);
        //this.UI.setText("시작하기 위해 'Enter'를 누르거나 '새 게임 시작'을 누르세요");

    }
}