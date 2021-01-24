package com.example.mytetris;

public class Iblock extends Block {
    public Iblock() {
        this.shape = new int[][][]{{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, {{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}}};
        this.x = 3;
        this.y = 0;
        this.width = 4;
        this.height = 4;
        this.rotation = 0;
        this.type = 1;
        this.NumOfRotation = 2;
    }
}
