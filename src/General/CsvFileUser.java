package General;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class CsvFileUser {

    //Read .csv file and put it into a List of String[]
    public static void readCSV(String fileName, List<String[]> data) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                data.add(line.split(";"));
            }
        } catch (Exception e) {
        }
    }

    public static String loadValue(String name, List<String[]> data, String valueResearched){
        int index=0;
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

    public static String loadValue(String name, List<String[]> data, String valueResearched, int level){
        int index=0;
        for (int j = 0; j < data.get(0).length; j++) {
            if (data.get(0)[j].equals(valueResearched)) {
                index = j;
            }
        }
        for (String[] element : data) {
            if (element[0].equals(name + level)) {
                return element[index];
            }
        }
        return "Error reading file";
    }
}
