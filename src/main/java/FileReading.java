import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine().substring(2));
        }
    }

//    public static void main(String[] args) {
//        try {
//            printFileContents("C:/Users/josep/duke/data/duke.txt");
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//    }

}