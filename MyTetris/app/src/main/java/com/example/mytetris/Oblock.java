package com.example.mytetris;

public class Oblock extends Block {
    public Oblock() {
        this.shape = new int[][][]{{{1, 1}, {1, 1}}};
        this.x = 4;
        this.y = 0;
        this.width = 2;
        this.height = 2;
        this.rotation = 0;
        this.type = 5;
        this.NumOfRotation = 1;
    }

    public int RightRotate() {
        return this.rotation = 0;
    }

    public int LeftRotate() {
        return this.rotation = 0;
    }
}
