package learning;

import base.NeuralNetwork;

import java.util.ArrayList;

@FunctionalInterface
public interface FitnessFunction{
     Float calculate(NeuralNetwork<ArrayList<Integer>> nn);
}
