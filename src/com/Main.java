package com;

import com.matrix.Matrix;

public class Main {

    public static void main(String[] args) {

        Matrix matrix = new Matrix(3, 4);
        matrix.randomize();

        System.out.println(matrix);
    }
}
