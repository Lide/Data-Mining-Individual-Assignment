package excluded;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Lisa Denzer on 06.03.2017.
 */
public class Main {

    public static void main(String[] args) {

    CSVFileReader fileReader = new CSVFileReader();
    DataCleaner dataCleaner = new DataCleaner();

    try {
        String[][] data = CSVFileReader.readDataFile("Data Mining - Spring 2017.csv",";;", "-",false);

        for (String[] line : data) {
            dataCleaner.cleanAge(line[1]);

            System.out.println(Arrays.toString(line));
        }

        System.out.println("Number of tuples loaded: "+ data.length);
    } catch (IOException e) {
        System.err.println(e.getLocalizedMessage());
    }

}
