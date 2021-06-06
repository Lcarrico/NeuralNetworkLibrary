package learning;

import base.NeuralNetwork;

import java.util.ArrayList;

/**
 * This interface will be the lambda function for the fitness score of the genetic algorithm.
 * @see GeneticAlgorithm
 */
@FunctionalInterface
public interface FitnessFunction{
     /**
      * This method will calculate the fitness score of a neural network.
      * @param nn This parameter is the NeuralNetwork input to calculate the fitness score of.
      * @return Float
      */
     Float calculate(NeuralNetwork<ArrayList<Integer>> nn);
}
