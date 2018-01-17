package aveh.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sforce.async.CSVReader;

public class CsvFileReader {
    public static List<List<String>> readFile1(String csvFile) {
        return readFile(csvFile, true);
    }

    public static List<List<String>> readFile2(String csvFullPath) {
        return readFile(csvFullPath, false);
    }

    private static List<List<String>> readFile(String csvFile, boolean useDefaultPath) {
        String csvFilePathFull = useDefaultPath == true ? Setup.CSV_FILE_PATH + csvFile : csvFile;
        System.out.println("csv files path: " + csvFilePathFull);
        List<List<String>> csvDataList = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFilePathFull));
            List<String> nextLine;
            csvDataList = new ArrayList<List<String>>();
            while ((nextLine = reader.nextRecord()) != null) {
                // nextLine[] is an array of values from the line
                csvDataList.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvDataList;
    }

}
