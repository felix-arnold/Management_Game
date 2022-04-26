package FileReaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 * This class contains the methods used to read a CSV file.
 */

public class CsvFileUser {

    /**
     * Reads the CSV file and load its content into a list.
     * <br>The CSV file specified with the argument fileName is loaded into a buffer and each of its lines are added as an element of the list of string specified in the data argument.
     * The file must use ";" as separator, as every line is beforehand split around it.
     *
     * @param fileName the name of the CSV file read
     * @param data     the list which will contain the content of the csv file
     */

    //Read .csv file and put it into a List of String[]
    public static void readCSV(String fileName, List<String[]> data) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                data.add(line.split(";"));
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Returns a specific data of a CSV file loaded into a list with the readCSV method.
     * <br> The CSV file must possess rows and columns names. The method browses through them to retrieve the right data.
     * @param name            the name of the row researched
     * @param data            the name of the list holding the data from a CSV file
     * @param valueResearched the name of the column researched
     * @return the data looked for, except in case of error
     */
    public static String loadValue(String name, List<String[]> data, String valueResearched) {
        int index = 0;
        for (int j = 0; j < data.get(0).length; j++) {
            if (data.get(0)[j].equals(valueResearched)) {
                index = j;
            }
        }
        for (String[] element : data) {
            if (element[0].equals(name)) {
                return element[index];
            }
        }
        return "Error reading file";
    }
}
