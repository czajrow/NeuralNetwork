package com;

import com.nn.NeuralNetwork;

public class Main {

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 8, 1);

        double[] inputs1 = {0, 0};
        double[] inputs2 = {0, 1};
        double[] inputs3 = {1, 0};
        double[] inputs4 = {1, 1};

        double[] outputs1 = {0};
        double[] outputs2 = {1};
        double[] outputs3 = {1};
        double[] outputs4 = {0};

        for (int i = 0; i < 100000; i++) {
            neuralNetwork.train(inputs1, outputs1);
            neuralNetwork.train(inputs2, outputs2);
            neuralNetwork.train(inputs3, outputs3);
            neuralNetwork.train(inputs4, outputs4);
        }

        double[] guess1 = neuralNetwork.feedforward(inputs1);
        double[] guess2 = neuralNetwork.feedforward(inputs2);
        double[] guess3 = neuralNetwork.feedforward(inputs3);
        double[] guess4 = neuralNetwork.feedforward(inputs4);


        System.out.format("target1= %.3g\n", outputs1[0]);
        System.out.format("guess1= %.3g\n\n", guess1[0]);

        System.out.format("target1= %.3g\n", outputs2[0]);
        System.out.format("guess2= %.3g\n\n", guess2[0]);

        System.out.format("target1= %.3g\n", outputs3[0]);
        System.out.format("guess3= %.3g\n\n", guess3[0]);

        System.out.format("target1= %.3g\n", outputs4[0]);
        System.out.format("guess4= %.3g\n\n", guess4[0]);
    }
}
