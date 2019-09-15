import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import commands.Command;
import exceptions.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    private static Storage storage;
    private static Ui ui;
    private TaskList taskList;

    /**
     * A Personal Assistant Chatbot, Duke, that helps a person to keep track of various things.
     * Constructor for Duke.
     * @param filePath The string representing the path to the file of which Duke loads data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.showDukeError(e); //save corrupted exceptions
            taskList = new TaskList();
        }
    }

    /**
     * This method runs Duke.
     */
    public void run() {
        ui.printHelloMsg();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (IOException e) {
                ui.showIOExceptionError(e);
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                ui.showNumberFormatException();
            }
        }
    }

    /**
     * Main function.
     * @param args Command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {
        String filePath = "C:/Users/josep/duke/data/duke.txt";
        new Duke(filePath).run();
    }
}
