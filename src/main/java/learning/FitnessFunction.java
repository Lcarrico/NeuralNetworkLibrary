package learning;

import base.NeuralNetwork;

import java.util.ArrayList;

/**
 * This interface will be the lambda function for the fitness score of the genetic algorithm.
 * @see GeneticAlgorithm
 */
@FunctionalInterface
public interface FitnessFunction{
     Float calculate(NeuralNetwork<ArrayList<Integer>> nn);
}
