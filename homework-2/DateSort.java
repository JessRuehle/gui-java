package edu.bloomu.homework2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates 10 random dates between the current date and the date 10,000 days from now.
 * They are then ordered by month.
 *
 * @author Jessica Ruehle
 */
public class DateSort {
    public static void main(String[] args) {

        // create an array of local dates of size 10
        LocalDate[] dates = new LocalDate[10];

        // a for loop that iterates through the whole array and assigns a random date
        // between today and ten thousand days from now to each index of the array
        for (int i = 0; i < dates.length; i++) {
            ThreadLocalRandom rand  = ThreadLocalRandom.current();
            LocalDate now = LocalDate.now();
            dates[i] = now.plusDays(rand.nextInt(0, 10_001));
        }

        // Arrays.sort organizes the array using custom comparator class
        Arrays.sort(dates, new DateComparator());

        // for loop to iterate through the array and print all the dates to the console
        // in an easy-to-read format
        for (int i = 0; i < dates.length; i++) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            System.out.println(dates[i].format(f));
        }
    }
}

/**
 * Orders the dates by month.
 */
class DateComparator implements Comparator<LocalDate> {

    // compare method gets the value of each month and subtracts them to compare them
    @Override
    public int compare(LocalDate a, LocalDate b) {
        return b.getMonthValue() - a.getMonthValue();
    }
}