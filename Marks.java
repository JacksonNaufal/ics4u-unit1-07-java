
/*
* This is a program that calculates mean, median
* after reading in a text file into an array.
*
* @author  Jackson Naufal
* @version 1.0
* @since   2020-09-30
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marks {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marks() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The generateMarks() function.
    *
    * @param arrayOfStudents the collection of students
    * @param arrayOfAssignments the collection of assignments
    * @return the generated marks
    */
    public static void generateMarks(final ArrayList<String> arrayOfStud, 
                                       final ArrayList<String> arrayOfWork) {

        // this is just a place holder!
        final Random random = new Random();

        final int studQuan = arrayOfStud.size();
        final int workQuan = arrayOfWork.size();

        final ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        final ArrayList<String> topRow = new ArrayList<String>();
        topRow.add(" ");
        for (int counter = 0; counter < workQuan; counter ++){
            topRow.add(arrayOfWork.get(counter));
        }

        table.add(topRow);

        for (int studCounter = 0; studQuan < studCounter; studCounter ++) {
            final ArrayList<String> studRow = new ArrayList<String>();
            studRow.add(arrayOfStud.get(studCounter));
            for (int workCounter = 0; workCounter < workQuan; workCounter++) {
                  final int mark = (int) Math.floor(random.nextGaussian() * 10 + 75);
                  studRow.add(String.valueOf(mark));
            }
            table.add(studRow);
        }

    final String fileName ="table.csv";
    final File file = new File(fileName);

        // Writing and formatting it to the file.
        try {
            final FileWriter fileWriter = new FileWriter(fileName, false);

            for (int row = 0; row < table.size(); row++) {
                final String line = String.join(",", table.get(row)) + ",\n";
                System.out.println(line);
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException error) {
            System.out.println("An error occured.");
            error.printStackTrace();
        }
    }

        public static void main(final String[] args) {
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssignments = new ArrayList<String>();
        final Path studentFilePath = Paths.get(args[0]);
        final Path assignmentFilePath = Paths.get(args[1]);
        final Charset charset = Charset.forName("UTF-8");

        // Reading the list of students from a text file.
        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
                // System.out.println(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        // Reading the list of assignments from a text file.
        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssignments.add(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        generateMarks(listOfStudents, listOfAssignments);

        System.out.println("\nDone.");
    }
}
