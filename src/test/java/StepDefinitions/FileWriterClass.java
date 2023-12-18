package StepDefinitions;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {

    public static void writeToFile(String filePath, String[] variables) {
        try (FileWriter writer = new FileWriter(filePath)) {

            for (String variable : variables) {
                writer.write(variable);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}