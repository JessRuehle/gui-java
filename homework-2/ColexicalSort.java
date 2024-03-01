package edu.bloomu.homework2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Takes a user-inputted set of words and sorts them alphabetically by the last letter.
 *
 *      Input:
 *      A KINGS TIME AS A RULER RISES AND FALLS LIKE THE SUN
 *
 *      Output:
 *      A A AND THE LIKE TIME SUN RULER AS RISES KINGS FALLS
 *
 * @author Jessica Ruehle
 */
public class ColexicalSort {
    public static void main(String[] args) {

        // prompt the user to input a line of text and declares an array of those words
        System.out.println("Enter a line of text: ");
        Scanner in = new Scanner(System.in);
        String[] words = in.nextLine().split(" ");

        // sorts the array based on the comparator method
        Arrays.sort(words, new ColexicalComparator());

        // iterates through the array and prints the results to screen while making
        // everything right-justified
        for (int i = 0; i < words.length; i++) {
            System.out.println(" ".repeat(findLongestString(words) - words[i].length())
                    + words[i]);
        }

    }


    /**
     * Returns the length of the longest string in the array.
     */
    private static int findLongestString(String[] words) {

        // keeps track of the length of the longest string
        int longestString = 0;

        // go through the array and check each word to see if it's the longest word by
        // comparing it to the length of longestString which stores the length of the
        // longest word so far
        for (int i = 0; i < words.length; i++) {
            if (longestString < words[i].length()) {
                longestString = words[i].length();
            }
        }
        return longestString;
    }
}

/**
 * Custom comparator to compare the last letter of every sting to sort by alphabetical
 * order of the last letter.
 */
class ColexicalComparator implements Comparator<String> {

    @Override
    public int compare(String one, String two) {
        // create an int to store the last letter value and make two words being
        // compared lowercase to avoid ASCII value problems
        int lastLet;
        String word1 = one.toLowerCase();
        String word2 = two.toLowerCase();

        // for loop that checks there are still two words to compare
        for (int i = 1; i <= Math.min(word1.length(), word2.length()); i++) {
            // compares the last letter of both words
            lastLet = word1.charAt(word1.length() - i) - word2.charAt(word2.length() - i);
            // if they are not the same letter, return a positive or negative number
            // based on how they compare
            if (lastLet != 0) {
                return lastLet;
            }
        }
        // return 0 if they are the same
        return 0;
    }
}
