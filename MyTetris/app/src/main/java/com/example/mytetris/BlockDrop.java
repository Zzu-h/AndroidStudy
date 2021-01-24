/*
package com.example.mytetris;

public class BlockDrop extends Thread {
    static int dropdelay = 1000;
    private Block block;
    private int[][] place;
    private int[][] temp;
    private int x_size;
    private int y_size;
    private TetrisUI ui;
    private boolean flag = false;

    public BlockDrop(GameRun run) {
        this.block = run.newBlock;
        this.place = run.place;
        this.temp = run.place;
        this.ui = run.ui;
        this.x_size = this.place[0].length;
        this.y_size = this.place.length;
        this.temp = new int[this.y_size][this.x_size];
        this.randRotate();
        this.Run();
    }

    private void Run() {
        try {
            this.save();

            for(int k = 0; k < this.y_size; ++k) {
                for(int y = 0; y < this.block.height; ++y) {
                    for(int x = 0; x < this.block.width; ++x) {
                        if (this.temp[this.block.y + y][this.block.x + x] != 1) {
                            this.place[this.block.y + y][this.block.x + x] = this.block.shape[this.block.rotation][y][x];
                        }

                        if (this.temp[this.block.y + y + 1][this.block.x + x] == 1 && this.block.shape[this.block.rotation][y][x] == 1) {
                            this.flag = true;
                        }
                    }
                }

                this.block.downShift();
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
        for(int i = (int)(Math.random() * 10.0D) % this.block.NumOfRotation; i > 0; --i) {
            this.block.RightRotate();
        }

    }
}
*/
