package com.matrix;

import java.util.Random;
import java.util.function.Function;

public class Matrix {

    // fields

    private int rows;
    private int cols;
    private double[][] data;

    // constructors

    public Matrix(int rows, int cols) {
        this(rows, cols, new double[rows][cols]);
    }

    private Matrix(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.data = matrix;
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

    public double[][] getData() {
        return data;
    }

    public void setData(int row, int col, double data) {
        this.data[row][col] = data;
    }

    // public methods

    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = random.nextDouble() * 2 - 1;
            }
        }
    }

    public double[] toArray() {
        double[] array = new double[rows * cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[index++] = data[i][j];
            }
        }
        return array;
    }

    public static Matrix fromArray(double[] array) {
        Matrix result = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; i++) {
            result.data[i][0] = array[i];
        }
        return result;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        if (a.rows != b.rows || a.cols != b.cols) {
            throw new IllegalArgumentException("dimensions are not same");
        }
        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return result;
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

    public static Matrix add(Matrix a, Matrix b) {
        if (a.rows != b.rows || a.cols != b.cols) {
            throw new IllegalArgumentException("dimensions are not same");
        }
        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.data[i][j] = a.data[i][j] + b.data[i][j];
            }
        }
        return result;
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

    public static Matrix transpose(Matrix matrix) {
        Matrix result = new Matrix(matrix.cols, matrix.rows);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                result.data[j][i] = matrix.data[i][j];
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.cols != b.rows) {
            throw new IllegalArgumentException("number of a columns must be equals to number of b rows");
        }
        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public void multiply(Matrix other) {
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

    public Matrix copy() {
        double[][] array = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = data[i][j];
            }
        }

        return new Matrix(rows, cols, array);
    }

    public void map(Function<Double, Double> function) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = data[i][j];
                data[i][j] = function.apply(value);
            }
        }
    }

    public static Matrix map(Matrix matrix, Function<Double, Double> function) {
        Matrix result = new Matrix(matrix.rows, matrix.cols);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                double value = matrix.data[i][j];
                result.data[i][j] = function.apply(value);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("| ");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]).append(" | ");
            }
            sb.append(System.lineSeparator()).append("| ");
        }
        sb.deleteCharAt(sb.length() - 2); // last '|' character
        return sb.toString();
    }
}
