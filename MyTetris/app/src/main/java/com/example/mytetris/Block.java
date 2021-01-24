package com.example.mytetris;

public abstract class Block {
    int x;
    int y;
    int type;
    int rotation;
    int NumOfRotation;
    int [][][] shape;
    int width;
    int height;
    
    public int RightRotate() {
        return this.rotation = (this.rotation + 1) % this.NumOfRotation;
    }

    public int LeftRotate() {
        return this.rotation = (this.rotation - 1 + this.NumOfRotation) % this.NumOfRotation;
    }

    public int rShift() {
        return this.x++;
    }

    public int lShift() {
        return this.x--;
    }

    public int downShift() {
        return this.y++;
    }

    public int[][] getShape() {
        return this.shape[this.rotation];
    }
}
