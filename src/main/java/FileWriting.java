import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

//    public static void main(String[] args) {
//        String file2 = "C:/Users/josep/duke/data/duke.txt";
//        try {
//            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }
}
