package com.example.mytetris;

public class Sblock extends Block {
    public Sblock() {
        this.shape = new int[][][]{{{0, 1, 1}, {1, 1, 0}, {0, 0, 0}}, {{1, 0, 0}, {1, 1, 0}, {0, 1, 0}}};
        this.x = 4;
        this.y = 0;
        this.width = 3;
        this.height = 3;
        this.rotation = 0;
        this.type = 6;
        this.NumOfRotation = 2;
    }
}
