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

    public void train(double[] inputArray, double[] targetArray) {

        // generating the hidden outputs
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(weights_IH, inputs);
        hidden.add(bias_H);

        // activation function
        hidden.map(sigmoid);

        // generating the output's output
        Matrix outputs = Matrix.multiply(weights_HO, hidden);
        outputs.add(bias_O);
        outputs.map(sigmoid);

        // Convert array to matrix object
        Matrix targets = Matrix.fromArray(targetArray);

        // calculate the error
        // ERROR = TARGETS - OUTPUTS
        Matrix outputErrors = Matrix.subtract(targets, outputs);

        // calculate gradient
        Matrix gradients = Matrix.map(outputs, dsigmoid);
        gradients.multiply(outputErrors);
        gradients.multiply(learningRate);

        // calculate deltas
        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix weight_HO_deltas = Matrix.multiply(gradients, hidden_T);

        // adjust the weights by deltas
        weights_HO.add(weight_HO_deltas);

        // Adjust the bias by its deltas (which is just the gradients)
        bias_O.add(gradients);

        // Calculate the hidden layer errors
        Matrix who_T = Matrix.transpose(weights_HO);
        Matrix hiddenErrors = Matrix.multiply(who_T, outputErrors);

        // Calculate hidden gradient
        Matrix hiddenGradient = Matrix.map(hidden, dsigmoid);
        hiddenGradient.multiply(hiddenErrors);
        hiddenGradient.multiply(learningRate);

        // Calcuate input->hidden deltas
        Matrix inputs_T = Matrix.transpose(inputs);
        Matrix weight_IH_deltas = Matrix.multiply(hiddenGradient, inputs_T);

        weights_IH.add(weight_IH_deltas);

        // adjust the bias by its deltas (which is just the gradients)
        bias_H.add(hiddenGradient);
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
