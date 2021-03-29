package com.github.amitagarwl.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvUtils {

    /**
     * Utils to read csv files and add extra column
     * @throws IOException
     */

    public static void update() throws IOException {

        String row = null;
        String path = "src/test/resources/read.csv";
        FileWriter csvWriter = new FileWriter("src/test/resources/write.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        while ((row = bufferedReader.readLine()) != null) {
            String[] data = row.split(",");
            String column_1 = data[1];
            String column_2 = data[2];
            String column_3 = column_1.concat(column_2);
            StringBuilder updateRaw = new StringBuilder(row);
            updateRaw.append(",").append(column_3);
            csvWriter.append(updateRaw).append("\n");
        }
        bufferedReader.close();
        csvWriter.close();


    }


}
