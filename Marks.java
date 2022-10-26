
/*
* This is a program that uses standered deviation
* and outputs students marks into a CSV file.
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
    * @param arrayOfStud the collection of students
    * @param arrayOfWork the collection of assignments
    */

    public static void generateMarks(final ArrayList<String> arrayOfStud,
                                       final ArrayList<String> arrayOfWork) {

        // this is just a place holder!
        final Random random = new Random();

        // gets the array size of students and work
        final int studQuan = arrayOfStud.size();
        final int workQuan = arrayOfWork.size();

        // creates the table array
        final ArrayList<ArrayList<String>> table =
                new ArrayList<ArrayList<String>>();

        // top row of the table
        final ArrayList<String> topRow = new ArrayList<String>();
        topRow.add(" ");
        for (int counter = 0; counter < workQuan; counter++) {
            topRow.add(arrayOfWork.get(counter));
        }

        // adds the top row
        table.add(topRow);

        // calculates the random marks, adds to  table in the student row.
        for (int studCounter = 0; studCounter < studQuan; studCounter++) {
            final ArrayList<String> studRow = new ArrayList<String>();
            studRow.add(arrayOfStud.get(studCounter));
            for (int workCounter = 0; workCounter < workQuan; workCounter++) {
                final int mark = (int) Math.floor(random.nextGaussian()
                                  * 10 + 75);
                studRow.add(String.valueOf(mark));
            }
            // adds to the table
            table.add(studRow);
        }

        // creating the CSV file
        final String fileName = "marks.csv";
        final File file = new File(fileName);

        // How the file is made with the fileWriter and joined together, split
        // each section by a ","
        try {
            final FileWriter fileWriter = new FileWriter(fileName, false);

            for (int row = 0; row < table.size(); row++) {
                final String line = String.join(",", table.get(row)) + ",\n";
                System.out.println(line);
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException error) {
            System.out.println("How are you here?");
            error.printStackTrace();
        }
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssignments = new ArrayList<String>();
        final Path studentFilePath = Paths.get(args[0]);
        final Path assignmentFilePath = Paths.get(args[1]);
        final Charset charset = Charset.forName("UTF-8");

        // reads in students file.
        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        // reads in assignments file
        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssignments.add(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        // outputs onto the file
        generateMarks(listOfStudents, listOfAssignments);

        // end of program
        System.out.println("\nDone.");
    }
}
