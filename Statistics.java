
/*
* This is a program that calculates mean, median
* after reading in a text file into an array.
*
* @author  Jackson Naufal
* @version 1.0
* @since   2020-09-30
*/

// These are some imports.
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @param quantity the number of integers within the array
    * @return the mean of the integers
    */
    public static float mean(final Integer[] arrayOfIntegers,
        final float quantity) {

        // this is my variable in my mean function
        float meanCalc = 0;

        // this is the loop to find the mean.
        for (int counter : arrayOfIntegers) {
            meanCalc += counter;
        }
        meanCalc = meanCalc / quantity;

        return meanCalc;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @param quantity the nuber of integers within the array
    * @return the median of the integers
    */
    public static float median(final Integer[] arrayOfIntegers,
        final float quantity) {

        // these are my variables in my median function.
        float medianCalc = 0;
        final double extra = 0.5;
        Arrays.sort(arrayOfIntegers);

        // this is the calculation for the median.
        if (quantity % 2 == 0) {
            medianCalc = arrayOfIntegers[(int) (quantity / 2 - extra)];
        } else {
            medianCalc = (arrayOfIntegers[(int) (quantity / 2 - extra)]
                         + arrayOfIntegers[(int) (quantity / 2 + extra)])
                         / 2;
        }
        // this returns the medians calculated number.
        return medianCalc;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        // These are my variables within my main function
        Integer tempNumber;
        final float meanCalc;
        final float medianCalc;
        final Integer[] arrayOfIntegers;
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        // this is the process for my program
        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        arrayOfIntegers = listOfNumbers.toArray(new Integer[0]);
        // This gathers the amount of ints in the array.
        final float quantity = arrayOfIntegers.length;

        // this is my output for my program.
        System.out.println("\nCalculating stats...");
        meanCalc = mean(arrayOfIntegers, quantity);
        medianCalc = median(arrayOfIntegers, quantity);

        System.out.println("The mean is: " + meanCalc);
        System.out.println("The median is: " + medianCalc);

        System.out.println("\nDone.");
    }
}
