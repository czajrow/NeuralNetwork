package com;

import com.matrix.Matrix;

public class Main {

    public static void main(String[] args) {
        // todo TESTY Matrixa

        Matrix matrix = new Matrix(3, 4);
        matrix.randomize();

        System.out.println(matrix.toString());
        System.out.println(matrix.toArray());
    }
}
