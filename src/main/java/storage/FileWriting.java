package storage;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {
    /**
     * This method writes a string text to the text file at the file path.
     * @param filePath The string which represents the file path of the text save file.
     * @param textToAdd The string which represents the content to be written to the text file.
     * @throws IOException The exception thrown when the filepath is invalid.
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * This method appends a string text to the text file at the file path.
     * @param filePath The string which represents the file path of the text save file.
     * @param textToAppend The string which represents the content to be written to the text file.
     * @throws IOException The exception thrown when the filepath is invalid.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
