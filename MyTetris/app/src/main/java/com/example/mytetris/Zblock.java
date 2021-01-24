package com.example.mytetris;

public class Zblock extends Block {
    public Zblock() {
        this.shape = new int[][][]{{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}}, {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}};
        this.x = 4;
        this.y = 0;
        this.width = 3;
        this.height = 3;
        this.rotation = 0;
        this.type = 7;
        this.NumOfRotation = 2;
    }
}
