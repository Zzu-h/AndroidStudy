package com.example.mytetris;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TetrisUI {
    private int x_size = 10;
    private int y_size = 22;
    private View tetris[][];

    private LinearLayout LinearLayout_main;
    private GridLayout GridLayout_screen;
    private TextView TextView_score;
    private Context main_context;

    public TetrisUI(Context context, LinearLayout L) {
        this.tetris = new View[this.y_size][this.x_size];

        LinearLayout_main = L;
        GridLayout_screen = L.findViewById(R.id.GridLayout_screen);
        TextView_score = L.findViewById(R.id.TextView_score);
        main_context = context;

        GridLayout_screen.setColumnCount(x_size);
        GridLayout_screen.setRowCount(y_size);

        this.mainUI();
    }

    private void mainUI() {

        for(int y = 0; y < this.y_size; ++y) {
            for(int x = 0; x < this.x_size; ++x) {
                TextView viewTemp = new TextView(main_context);
                viewTemp.setWidth(105);
                viewTemp.setHeight(67);
                viewTemp.setBackground(main_context.getResources().getDrawable(R.drawable.border_cell));
                this.tetris[y][x] = viewTemp;
                GridLayout_screen.addView(this.tetris[y][x]);
            }
        }
    }

    public void setLayout(int[][] arr) {
        for(int y = 0; y < this.y_size; ++y) {
            for(int x = 0; x < this.x_size; ++x) {
                if (arr[y][x] == 1) {
                    this.tetris[y][x].setBackground(main_context.getResources().getDrawable(R.drawable.border_cell_block));
                } else {
                    this.tetris[y][x].setBackground(main_context.getResources().getDrawable(R.drawable.border_cell));
                }
            }
        }

    }

    public void setScore(int point) {
        this.TextView_score.setText("" + point);
    }

    public void setText(String str) {
        this.TextView_score.setText(str);
    }
    public LinearLayout getLinearLayout_main(){return this.LinearLayout_main;}

}
