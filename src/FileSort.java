import java.io.*;
import java.util.ArrayList;


/**
 * Created by AlnAvalon
 *
 * The program sorts the contents of the file.
 * The input file contains one of two types of data: integer or string.
 * The data is written in a column (each line of the file is a new element). Lines can contain any non-whitespace characters.
 * The result of the program is the creation of a new file with the contents of the input file, sorted in ascending or descending order.
 * For sorting, the lexicographic order is used (by character codes).
 *
 * The Insertion sort algorithm and its use for sorting the contents of the file is independently implemented (library sorting functions were not used).
 * Because this algorithm is not effective on large amounts of data, Ш can assume that the input file contains a small number of elements: not more than 100.
 * All possible types of errors are processed.
 * The program should not “crash”.
 * If it is impossible to continue the program after an error, the program should inform the user about this, indicating the reason for the failure.
 *
 * The name of the input, output file, sort mode, as well as the type of content are set at startup through command line arguments. Examples of starting with program arguments:
 * "C:\in.txt C:\out.txt -i -a" (for integers in ascending order)
 * "C:\in.txt C:\out.txt -i -d" (for integers descending)
 * "C:\in.txt C:\out.txt -s -a" (for strings ascending)
 *
 * Example:
 * C:\in.txt:
 * 323
 * 4678
 * 9
 * 234
 * 12
 * 15
 * C:\out.txt:
 * 9
 * 12
 * 15
 * 234
 * 323
 * 4678
 */
public class FileSort {
    public static void main(String[] args) {
        try {
            String in = args[0];
            String out = args[1];
            String argument = args[2] + " " + args[3];

            if (argument.contains("-i -a")) {
                sortIntegerIncrease(in, out);
                System.out.println("The program has successfully sorted integers in ascending order.");
            } else if (argument.contains("-i -d")) {
                sortIntegerDecrease(in, out);
                System.out.println("The program has successfully sorted integers in descending order.");
            } else if (argument.contains("-s -a")) {
                sortStringIncrease(in, out);
                System.out.println("The program has successfully sorted strings by character codes.");
            }
            else
                System.out.println("Invalid command line arguments selected. Restart the program with valid command line arguments.");
        }
        catch (ArrayIndexOutOfBoundsException e)  {
            System.out.println("No command line arguments selected. Restart the program with command line arguments.");
        }
    }

    public static void sortIntegerIncrease(String in, String out) {
        try {
            ArrayList<Integer> list = inputIntegerRead(in);
            sortIncreaseInteger(list);
            outputIntegerWrite(list, out);
        }
        catch (FileNotFoundException e) {
            System.out.println("Read/write error. Restart the program by entering valid command line arguments.");
        }
        catch (Exception e) {
            System.out.println("The program is launched in the mode of sorting integers. A non-numeric value or a fractional number was detected." +
                    " Correct the input file and restart the program, or restart the program by entering valid command line arguments.");
        }
    }

    public static void sortIntegerDecrease(String in, String out) {
        try {
            ArrayList<Integer> list = inputIntegerRead(in);
            sortDecreaseInteger(list);
            outputIntegerWrite(list, out);
        } catch (FileNotFoundException e) {
            System.out.println("Read/write error. Restart the program by entering valid command line arguments.");
        } catch (Exception e) {
            System.out.println("The program is launched in the mode of sorting integers. A non-numeric value or a fractional number was detected." +
                    " Correct the input file and restart the program, or restart the program by entering valid command line arguments");
        }
    }

    public static void sortStringIncrease(String in, String out)  {
        try {
            ArrayList<String> list = inputTextReader(in);
            sortString(list);
            outputTextWriter(list, out);
        }
        catch (FileNotFoundException e)  {
            System.out.println("Read/write error. Restart the program by entering valid command line arguments.");
        }
        catch (Exception e) {
            System.out.println("Unknown error. Please contact to developer");
        }

    }

    public static ArrayList<String> inputTextReader(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String s = reader.readLine();
            if (!s.contentEquals(""))
                list.add(s);
        }
        reader.close();
        return list;
    }
    /** Method for writing a sorted  String list to an output file**/
    public static void outputTextWriter(ArrayList<String> list, String fileName) throws Exception {
        FileOutputStream writer = new FileOutputStream(fileName);
        for (String a :
                list) {
            String s = a + "\r\n";
            writer.write(s.getBytes());
        }
        writer.close();
    }

    /**
     * Method for reading a file with integers and writing them to a  Integer list
     **/
    public static ArrayList<Integer> inputIntegerRead(String fileName) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<Integer> list = new ArrayList<>();

        while (reader.ready()) {
            int data = Integer.parseInt(reader.readLine());
            list.add(data);
        }
        reader.close();
        return list;
    }

    /**
     * Method for writing a sorted Integer list to an output file
     **/
    public static void outputIntegerWrite(ArrayList<Integer> list, String fileName) throws Exception {
        FileOutputStream writer = new FileOutputStream(fileName);
        for (int a :
                list) {
            String s = a + "\r\n";
            writer.write(s.getBytes());
        }
        writer.close();

    }

    /**
     * Method to sort an Integer list in ascending order
     **/
    public static void sortIncreaseInteger(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) < list.get(j - 1))
                    swap(list, j, j - 1);
            }
        }
    }

    /**
     * Method to sort an Integer list in descending order
     **/
    public static void sortDecreaseInteger(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) > list.get(j - 1))
                    swap(list, j, j - 1);
            }
        }
    }

    public static void sortString(ArrayList<String> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j).compareTo(list.get(j - 1)) < 0)
                    swapString(list, j, j - 1);
            }
        }
    }

    /**
     * Helper method (transposition of neighboring values) for sorting a Integer list
     **/
    public static void swap(ArrayList<Integer> list, int a, int b) {
        int c = list.get(a);
        list.set(a, list.get(b));
        list.set(b, c);
    }

    /**
     * Helper method (transposition of neighboring values) for sorting a String list
     **/
    public static void swapString(ArrayList<String> list, int a, int b) {
        String c = list.get(a);
        list.set(a, list.get(b));
        list.set(b, c);
    }
}
