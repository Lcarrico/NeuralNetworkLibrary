package utils;

import base.NeuralNetwork;
import learning.FitnessFunction;

import java.util.ArrayList;

/**
 * This class will contain some standard fitness functions that may be used with a genetic algorithm.
 */
public class FitnessFunctions {

    /**
     * This method returns a fitness function that calculates the negative sum of absolute differences.
     * @param inputs This parameter will be the input values to be sent to the neural network for calculations.
     * @param expectedOutput This parameter will be the expected output of the neural network.
     * @return Float
     */
    public static FitnessFunction NegativeSumOfAbsoluteDifferences(ArrayList<ArrayList<Float>> inputs, ArrayList<ArrayList<Integer>> expectedOutput){
        return new FitnessFunction() {
            @Override
            public Float calculate(NeuralNetwork<ArrayList<Integer>> nn) {

                ArrayList<ArrayList<Integer>> calculatedOutput = nn.calcAll(inputs);

                float total = 0f;

                for (int i = 0; i < calculatedOutput.size(); i++){
                    for (int j = 0; j < calculatedOutput.get(i).size(); j++){
                        total -= Math.abs(calculatedOutput.get(i).get(j) - expectedOutput.get(i).get(j));
                    }
                }
                return total;
            }
        };

    }
}
