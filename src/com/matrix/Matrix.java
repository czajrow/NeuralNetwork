package com.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {

    // fields

    private int rows;
    private int cols;
    private double[][] data;
    private Random random;

    // constructors

    public Matrix(int rows, int cols) {
        this(rows, cols, new double[rows][cols]);
    }

    private Matrix(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.data = matrix;
        this.random = new Random();
    }

    // getters and setters

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double getData(int row, int col) {
        return data[row][col];
    }

    public void setData(int row, int col, double data) {
        this.data[row][col] = data;
    }

    // public methods

    public void randomize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = random.nextDouble();
            }
        }
    }

    public List<Double> toArray() {
        ArrayList<Double> array = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array.add(data[i][j]);
            }
        }
        return array;
    }

    public void transpose() {
        double[][] result = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = data[i][j];
            }
        }
        data = result;
    }

    public Matrix copy() {
        return new Matrix(rows, cols, new double[rows][cols]);
    }

    public void add(Matrix other) {
        if (other.rows != this.rows || other.cols != this.cols) {
            throw new IllegalArgumentException("dimensions are not same");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += other.data[i][j];
            }
        }
    }

    public void add(double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += scalar;
            }
        }
    }

    public void subtract(Matrix other) {
        if (other.rows != this.rows || other.cols != this.cols) {
            throw new IllegalArgumentException("dimensions are not same");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] -= other.data[i][j];
            }
        }
    }

    public void subtract(double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] -= scalar;
            }
        }
    }

    public void multiply(Matrix other) {
        if (other.rows != this.rows || other.cols != this.cols) {
            throw new IllegalArgumentException("dimensions are not same");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= other.data[i][j];
            }
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= scalar;
            }
        }
    }

    // static methods

    public static Matrix dot(Matrix a, Matrix b) {
        if (a.cols!= b.rows) {
            throw new IllegalArgumentException("number of a columns must be equals to number of b rows");
        }
        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }
}
