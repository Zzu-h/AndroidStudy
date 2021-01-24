package com.example.mytetris;

public class BlockMove extends Thread {
    private Block block;
    private TetrisUI ui;
    private Thread t;
    private int x;
    private int y;
    private int[][] place;
    private int[][] temp;
    private int k;

    public BlockMove(GameRun run, int key) {
        this.block = run.newBlock;
        this.place = run.place;
        this.t = run.T;
        this.ui = run.ui;
        this.k = key;
        this.x = this.place[0].length;
        this.y = this.place.length;
        this.temp = new int[this.y][this.x];
        this.ActionRouter();
        System.out.println(this.k);
    }

    private void ActionRouter() {
        switch(this.k) {
            case 10:
                this.PauseGame();
                break;
            case 27:
                this.EndGame();
                break;
            case 32:
            case 98:
                this.VerticalDrop();
                break;
            case 37:
            case 100:
                this.LeftShift();
                break;
            case 38:
            case 105:
                this.RightRotate();
                break;
            case 39:
            case 102:
                this.RightShift();
                break;
            case 40:
            case 101:
                this.DownShift();
                break;
            case 103:
                this.LeftRotate();
        }

        this.setLayout();
    }

    private void LeftShift() {
        if (this.block.x != 0) {
            boolean flag = false;

            for(int x = 0; x < this.block.width; ++x) {
                for(int y = 0; y < this.block.height; ++y) {
                    if (this.block.shape[this.block.rotation][y][x] == 1 && this.place[this.block.y + y][this.block.x + x - 1] == 1) {
                        flag = true;
                    }
                }
            }

            if (!flag) {
                this.block.lShift();
            }
        }
    }

    private void RightShift() {
        int x_size = this.x - 5;
        boolean flag = false;

        for(int x = 0; x < this.block.width; ++x) {
            for(int y = 0; y < this.block.height; ++y) {
                if (this.block.shape[this.block.rotation][y][x] == 1 && this.place[this.block.y + y][this.block.x + x + 1] == 1) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.block.rShift();
        }
    }

    private boolean DownShift() {
        boolean flag = false;

        for(int y = 0; y < this.block.height; ++y) {
            for(int x = 0; x < this.block.width; ++x) {
                if (this.place[this.block.y + y + 1][this.block.x + x] == 1 && this.block.shape[this.block.rotation][y][x] == 1) {
                    flag = true;
                }
            }
        }

        if (flag) {
            return true;
        } else {
            this.block.downShift();
            return false;
        }
    }

    private void RightRotate() {
        this.block.RightRotate();
    }

    private void LeftRotate() {
        this.block.LeftRotate();
    }

    private void VerticalDrop() {
        for(int y_ = 0; y_ < this.y && !this.DownShift(); ++y_) {
        }

        this.t.interrupt();
    }

    private void PauseGame() {
        try {
            GameState.GamePause();
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
        }

    }

    private void EndGame() {
        GameState.State = 4;
    }

    private void setLayout() {
        this.save();

        for(int y = 0; y < this.block.height; ++y) {
            for(int x = 0; x < this.block.width; ++x) {
                if (this.temp[this.block.y + y][this.block.x + x] != 1) {
                    this.place[this.block.y + y][this.block.x + x] = this.block.shape[this.block.rotation][y][x];
                }
            }
        }

        this.ui.setLayout(this.place);
        this.load();
    }

    private void save() {
        for(int y = 0; y < this.y; ++y) {
            for(int x = 0; x < this.x; ++x) {
                this.temp[y][x] = this.place[y][x];
            }
        }

    }

    private void load() {
        for(int y = 0; y < this.y; ++y) {
            for(int x = 0; x < this.x; ++x) {
                this.place[y][x] = this.temp[y][x];
            }
        }

    }
}
