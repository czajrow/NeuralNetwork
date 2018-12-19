package com.nn;

import com.matrix.Matrix;

public class NeuralNetwork {

    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;

    private double learningRate = 0.1;

    private Matrix weights_IH;
    private Matrix weights_HO;
    private Matrix bias_H;
    private Matrix bias_O;

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

    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private static double dsigmoid(double y) {
        return y * (1.0 - y);
    }
}
