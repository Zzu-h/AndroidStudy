package com.example.mytetris;

import android.view.View;
import android.widget.Button;

public class GameRun extends Thread {
    static int dropdelay = 1000;
    private int[][] temp;
    private boolean flag = false;
    
    
    protected Thread T;
    protected int[][] place;
    protected int x_size;
    protected int y_size;
    protected TetrisUI ui;
    protected Block newBlock;
    protected ScoreBoard point;

    public GameRun(TetrisUI UI, ScoreBoard SB) {
        System.out.println(GameState.State);
        this.ui = UI;
        this.T = new Thread();
        this.point = SB;
        this.newBlock = this.createBlock();
        this.x_size = 10;
        this.y_size = 22;
        this.setup();

        this.temp = new int[this.y_size][this.x_size];
        
        this.button();
        //run();
    }

    public void run() {
        this.point.update(0);

        try {
            while(true) {
                System.out.print("");

                while(GameState.State == 2) {

                    /*this.T = new BlockDrop(this);
                    this.T.start();*/
                    this.blockdrop();
                    this.newBlock = this.createBlock();
                    this.print();
                    this.delCheck();
                    this.endCheck();
                }
            }
        } catch (Exception var2) {
            System.out.println("Test");
            GameState.State = 4;
        }
    }

    private void setup() {
        int null_x = this.x_size + 5;
        int null_y = this.y_size + 5;
        this.place = new int[null_y][null_x];
        this.initPlace();
    }

    private void print() {
        for(int y_ = 0; y_ < this.y_size; ++y_) {
            for(int x_ = 0; x_ < this.x_size; ++x_) {
                System.out.print(this.place[y_][x_] + " ");
            }

            System.out.println();
        }

    }

    private Block createBlock() {
        int RandNum = (int)(Math.random() * 10.0D % 7.0D);
        switch(RandNum) {
            case 0:
                return new Zblock();
            case 1:
                return new Iblock();
            case 2:
                return new Tblock();
            case 3:
                return new Jblock();
            case 4:
                return new Lblock();
            case 5:
                return new Oblock();
            case 6:
                return new Sblock();
            default:
                return new Iblock();
        }
    }

    private void delCheck() {
        int count = 0;
        boolean flag = true;

        int i;
        for(i = 0; i < this.y_size; ++i) {
            for(int k = 0; k < this.x_size && this.place[i][k] == 1; ++k) {
                if (k == this.x_size - 1) {
                    this.delLine(i);
                    ++count;
                }
            }
        }

        if (count != 0) {
            int score = 100;
            --count;
            i = score * (1 << count);
            System.out.println(i);
            this.point.update(i);
        }

    }

    private void delLine(int idx) {
        for(int i = idx; i > 0; --i) {
            for(int k = 0; k < this.x_size; ++k) {
                this.place[i][k] = this.place[i - 1][k];
            }
        }

    }

    private void endCheck() {
        for(int k = 0; k < this.x_size; ++k) {
            if (this.place[0][k] == 1) {
                GameState.GameEnd();
                break;
            }
        }

    }

    public int[][] getPlace() {
        return this.place;
    }

    /*public void setPlace(int[][] arr) {
        this.place = arr;
    }*/

    public void initPlace() {
        int null_x = this.x_size + 5;
        int null_y = this.y_size + 5;

        for(int j = 0; j < null_y; ++j) {
            for(int i = 0; i < null_x; ++i) {
                if (j < this.y_size && i < this.x_size) {
                    this.place[j][i] = 0;
                } else {
                    this.place[j][i] = 1;
                }
            }
        }

    }

    public void button(){
        ui.getLinearLayout_main().findViewById(R.id.Button_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameState.State == 2)
                    new BlockMove(GameRun.this,39);
            }
        });
        ui.getLinearLayout_main().findViewById(R.id.Button_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameState.State == 2)
                    new BlockMove(GameRun.this,37);
            }
        });
        ui.getLinearLayout_main().findViewById(R.id.Button_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameState.State == 2)
                    new BlockMove(GameRun.this,38);
            }
        });
        ui.getLinearLayout_main().findViewById(R.id.Button_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameState.State != 2) {
                    GameState.State = 2;
                    run();
                }
                else
                    new BlockMove(GameRun.this,32);
            }
        });
    }


    public void blockdrop() {
        this.randRotate();
        this.Run();
    }

    private void Run() {
        try {
            this.save();
            for(int k = 0; k < this.y_size; ++k) {
                for(int y = 0; y < this.newBlock.height; ++y) {
                    for(int x = 0; x < this.newBlock.width; ++x) {
                        if (this.temp[this.newBlock.y + y][this.newBlock.x + x] != 1) {
                            this.place[this.newBlock.y + y][this.newBlock.x + x] = this.newBlock.shape[this.newBlock.rotation][y][x];
                        }

                        if (this.temp[this.newBlock.y + y + 1][this.newBlock.x + x] == 1 && this.newBlock.shape[this.newBlock.rotation][y][x] == 1) {
                            this.flag = true;
                        }
                    }
                }

                this.newBlock.downShift();
                this.ui.setLayout(this.place);
                if (this.flag) {
                    return;
                }

                this.load();
                Thread.sleep((long)dropdelay);
            }
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

    }

    private void save() {
        for(int y = 0; y < this.y_size; ++y) {
            for(int x = 0; x < this.x_size; ++x) {
                this.temp[y][x] = this.place[y][x];
            }
        }

    }

    private void load() {
        for(int y = 0; y < this.y_size; ++y) {
            for(int x = 0; x < this.x_size; ++x) {
                this.place[y][x] = this.temp[y][x];
            }
        }

    }

    private void randRotate() {
        for(int i = (int)(Math.random() * 10.0D) % this.newBlock.NumOfRotation; i > 0; --i) {
            this.newBlock.RightRotate();
        }

    }
}
