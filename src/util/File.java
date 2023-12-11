package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class File {

    private static final String DEFAULT_FOLDER = "input";

    public static ArrayList<String> readLines(String fileName) {
        return readLines(DEFAULT_FOLDER, fileName);
    }

    public static ArrayList<String> readLines(String directory, String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        String filePath = Paths.get(directory, fileName).toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }
}
