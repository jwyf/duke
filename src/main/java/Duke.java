import Command.Command;
import Parser.Parser;
import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    /**
     * A Personal Assistant Chatbot that helps a person to keep track of various things.
     */

    private static Storage storage;
    private static Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.showDukeError(e); //save corrupted exception
            taskList = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        String filePath = "C:/Users/josep/duke/data/duke.txt";
        new Duke(filePath).run();
    }
}
