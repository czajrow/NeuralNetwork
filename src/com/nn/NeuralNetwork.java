package com.nn;

import com.matrix.Matrix;

import java.util.function.Function;

public class NeuralNetwork {

    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;

    private double learningRate = 0.1;

    private Matrix weights_IH;
    private Matrix weights_HO;
    private Matrix bias_H;
    private Matrix bias_O;

    private final Function<Double, Double> sigmoid = (x) -> 1.0 / (1.0 + Math.exp(-x));

    private final Function<Double, Double> dsigmoid = (y) -> 1.0 * (1.0 - y);


    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;

        weights_IH = new Matrix(hiddenNodes, inputNodes);
        weights_HO = new Matrix(outputNodes, hiddenNodes);

        bias_H = new Matrix(hiddenNodes, 1);
        bias_O = new Matrix(outputNodes, 1);

        weights_IH.randomize();
        weights_HO.randomize();

        bias_H.randomize();
        bias_O.randomize();
    }

    public double[] feedforward(double[] inputArray) {
        return guess(inputArray).toArray();
    }

    private Matrix guess(double[] inputArray) {

        // generating the hidden outputs
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(weights_IH, inputs);
        hidden.add(bias_H);

        // activation function
        hidden.map(sigmoid);

        // generating the output's output
        Matrix output = Matrix.multiply(weights_HO, hidden);
        output.add(bias_O);
        output.map(sigmoid);

        //returning data
        return output;
    }
}
