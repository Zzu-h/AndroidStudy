package com.example.mytetris;

public class Tblock extends Block {
    public Tblock() {
        this.shape = new int[][][]{{{1, 0, 0}, {1, 1, 0}, {1, 0, 0}}, {{1, 1, 1}, {0, 1, 0}, {0, 0, 0}}, {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}}};
        this.x = 3;
        this.y = 0;
        this.width = 3;
        this.height = 3;
        this.rotation = 0;
        this.type = 2;
        this.NumOfRotation = 4;
    }
}
