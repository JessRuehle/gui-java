package edu.bloomu.homework2;

import java.util.concurrent.ThreadLocalRandom;

public class CupOfDice implements Comparable<CupOfDice> {

    private final int[] dice;
    private final int sides;

    /**
     * Creates a cup of dice with random initial values.
     */
    public CupOfDice(int numDice, int sides) {
        this.dice = new int[numDice];
        this.sides = sides;
        roll();
    }

    /**
     * Creates a cup of 6-sided dice with random initial values.
     */
    public CupOfDice(int numDice) {
        this(numDice, 6);
    }

    /**
     * Rolls each die in the cup.
     *
     * @return a copy of the current dice values
     */
    public int[] roll() {
        for (int i = 0; i < dice.length; i++) {
            roll(i);
        }
        return dice.clone();
    }

    /**
     * Rolls a specified die.
     *
     * @return a copy of the current dice values
     */
    public int[] roll(int i) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        dice[i] = rand.nextInt(1, sides + 1);
        return dice.clone();
    }

    /**
     * Determines the weight of set of rolled dice. The weight is the largest number of
     * matching die values rolled.
     */
    public int getWeight() {
        // an array to track the number of times a certain die number is rolled
        int[] numSides = new int[sides + 1];
        // int to store the value of a die rolled at a certain index
        int dieValue;
        // counter to track the largest weight
        int weight = 0;

        // for loop to iterate through the array of rolled dice
        for (int i = 0; i < dice.length; i++) {
            // set the dieValue equal to the value of the die rolled at the ith index
            // of the array. Then, add 1 to the index of the array that's tracking the
            // amount of times a certain number has been rolled
            dieValue = dice[i];
            numSides[dieValue]++;

            // if the index tracking the number of times that value has been rolled has
            // exceeded the current heaviest weight, update the weight to the new
            // heaviest
            if (numSides[dieValue] > weight) {
                weight = numSides[dieValue];
            }
        }
        // returns the heaviest weight
        return weight;
    }

    /**
     * Returns the sum of all the die values rolled.
     */
    public int getSum() {
        // counter to keep the sum of the dice
        int sum = 0;

        // for loop to add the die value to the sum counter
        for (int i = 0; i < dice.length; i++) {
            sum += dice[i];
        }

        // return the final sum
        return sum;
    }

    /**
     * Outputs a custom String for this class.
     */
    @Override
    public String toString() {
        // declare the start of the string
        String output = "[";

        // iterate through the array and add the die values to the string
        for (int i = 0; i < dice.length; i++) {
            output += dice[i];
            // if you reach the end of the array, add a closing bracket. Otherwise, add
            // a comma and a space for the next number to go after
            if (i == dice.length - 1) {
                output += "] ";
            } else {
                output += ", ";
            }
        }

        // add the weight and the sum to the string
        output += "weight = " + getWeight() + " ";
        output += "sum = " + getSum();

        // return the final, concatinated string
        return output;
    }

    /**
     * Overrides the compareTo method to compare the rolled dice by weight and sum.
     */
    @Override
    public int compareTo(CupOfDice cup) {
        // if the weight of both cups is the same, compare the sums. Otherwise, compare
        // the weights
        if (getWeight() == cup.getWeight()) {
            return cup.getSum() - getSum();
        } else {
            return cup.getWeight() - getWeight();
        }
    }
}
