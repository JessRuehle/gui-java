package edu.bloomu.homework2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 10 cups of 5, six-sided die each are rolled. The results of each roll are ordered by
 * weight and sum. The weight of a roll is the most amount of times any one side is
 * rolled, and the sum is the total of all the die values added together.
 *
 * Example:
 *
 * [2, 1, 3, 3, 6] weight = 2 sum = 15
 *
 * @author Jessica Ruehle
 */
public class DiceSort {
    public static void main(String[] args) {

        // create a CupOfDice array of size 10 called cups
        CupOfDice[] cups = new CupOfDice[10];

        // iterate through a for loop to create a new CupOfDice object at each index
        // and call on .roll to roll each cup of dice
        for (int i = 0; i < cups.length; i++) {
            cups[i] = new CupOfDice(5);
            cups[i].roll();
        }

        // sort the array using Arrays.sort and the custom comparator
        Arrays.sort(cups, new DiceComparator());

        // iterate through the array and print the results to screen
        for (int i = 0; i < cups.length; i++) {
            System.out.println(cups[i]);
        }
    }
}

/**
 * A comparator for the Cups of Dice class.
 */
class DiceComparator implements Comparator<CupOfDice> {

    // using custom comparable method, compare the two cup of dice passed and sort the
    // array accordingly
    @Override
    public int compare(CupOfDice a, CupOfDice b) {
        return a.compareTo(b);
    }
}

/*

if b is true, implement E1, if not implement E2
b? E1 : E2
 */