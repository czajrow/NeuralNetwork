package com.matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    private Matrix m1;
    private Matrix m2;
    private Matrix m3;
    private Matrix m4;
    private Matrix m5;
    private Matrix m6;

    @Before
    public void setUp() {
        m1 = new Matrix(2, 3);
        m1.setData(0, 0, 1);
        m1.setData(0, 1, 2);
        m1.setData(0, 2, 3);
        m1.setData(1, 0, 4);
        m1.setData(1, 1, 5);
        m1.setData(1, 2, 6);

        m2 = new Matrix(3, 2);
        m2.setData(0, 0, 2);
        m2.setData(0, 1, 4);
        m2.setData(1, 0, 6);
        m2.setData(1, 1, 8);
        m2.setData(2, 0, 0);
        m2.setData(2, 1, 10);

        m3 = new Matrix(2, 3);
        m3.setData(0, 0, 2);
        m3.setData(0, 1, 4);
        m3.setData(0, 2, 6);
        m3.setData(1, 0, 8);
        m3.setData(1, 1, 10);
        m3.setData(1, 2, 12);

        m4 = new Matrix(2, 2);
        m4.setData(0, 0, 14);
        m4.setData(0, 1, 50);
        m4.setData(1, 0, 38);
        m4.setData(1, 1, 116);

        m5 = new Matrix(2, 3);
        m5.setData(0, 0, 0);
        m5.setData(0, 1, 1);
        m5.setData(0, 2, 2);
        m5.setData(1, 0, 3);
        m5.setData(1, 1, 4);
        m5.setData(1, 2, 5);

        m6 = new Matrix(3, 2);
        m6.setData(0, 0, 0);
        m6.setData(1, 0, 1);
        m6.setData(2, 0, 2);
        m6.setData(0, 1, 3);
        m6.setData(1, 1, 4);
        m6.setData(2, 1, 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void randomize() {
        m1.randomize();
        m2.randomize();
        m3.randomize();
    }

    @Test
    public void toArray() {
        double[] result = m1.toArray();
        double[] expected = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};

        assertArrayEquals(expected, result, 0.001);
    }

    @Test
    public void fromArray() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        Matrix result = Matrix.fromArray(array);

        assertArrayEquals(m1.toArray(), result.toArray(), 0.001);
    }

    @Test
    public void subtract() {
        Matrix result = Matrix.subtract(m3, m1);
        assertArrayEquals(m1.toArray(), result.toArray(), 0.001);
    }

    @Test
    public void subtract1() {
        m3.subtract(m1);
        assertArrayEquals(m1.toArray(), m3.toArray(), 0.001);
    }

    @Test
    public void subtract2() {
        m1.subtract(1);
        assertArrayEquals(m5.toArray(), m1.toArray(), 0.001);
    }

    @Test
    public void add() {
        Matrix result = Matrix.add(m1, m1);
        assertArrayEquals(m3.toArray(), result.toArray(), 0.001);
    }

    @Test
    public void add1() {
        m1.add(m1);
        assertArrayEquals(m3.toArray(), m1.toArray(), 0.001);

    }

    @Test
    public void add2() {
        m5.add(1);
        assertArrayEquals(m1.toArray(), m5.toArray(), 0.001);
    }

    @Test
    public void transpose() {
        Matrix result = Matrix.transpose(m5);
        assertArrayEquals(m6.toArray(), result.toArray(), 0.001);
    }

    @Test
    public void multiply() {
        Matrix result = Matrix.multiply(m1, m2);
        assertArrayEquals(m4.toArray(), result.toArray(), 0.001);
    }

    @Test
    public void multiply1() {
        m1.multiply(2);
        assertArrayEquals(m3.toArray(), m1.toArray(), 0.001);
    }

    @Test
    public void copy() {
        Matrix result = m1.copy();
        assertArrayEquals(m1.toArray(), result.toArray(), 0.001);
        assertEquals(m1.getRows(), result.getRows());
        assertEquals(m1.getCols(), result.getCols());

        result.setData(0, 0, 13);
        assertNotEquals(m1.getData(0, 0), result.getData(0, 0));
    }
}
