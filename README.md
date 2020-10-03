# FileSort

Simple java console application made for a job interview

The task was:
 * The program sorts the contents of the file.
 * The input file contains one of two types of data: integer or string.
 * The data is written in a column (each line of the file is a new element). Lines can contain any non-whitespace characters.
 * The result of the program is the creation of a new file with the contents of the input file, sorted in ascending or descending order.
 * For sorting, the lexicographic order is used (by character codes).
 * The Insertion sort algorithm and its use for sorting the contents of the file is independently implemented (library sorting functions must not be used).
 * Because this algorithm is not effective on large amounts of data, you can assume that the input file contains a small number of elements: not more than 100.
 * All possible types of errors are processed.
 * The program should not “crash”.
 * If it is impossible to continue the program after an error, the program should inform the user about this, indicating the reason for the failure.
 
 * The name of the input, output file, sort mode, as well as the type of content are set at startup through command line arguments. Examples of starting with program arguments:
 * "C:\in.txt C:\out.txt -i -a" (for integers in ascending order)
 * "C:\in.txt C:\out.txt -i -d" (for integers descending)
 * "C:\in.txt C:\out.txt -s -a" (for strings ascending)
