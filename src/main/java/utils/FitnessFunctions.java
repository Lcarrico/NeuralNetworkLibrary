package utils;

import base.NeuralNetwork;
import learning.FitnessFunction;

import java.util.ArrayList;

public class FitnessFunctions {

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
