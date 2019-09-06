import Task.TaskList;

import java.io.FileNotFoundException;


public class Duke {
    /**
     * A Personal Assistant Chatbot that helps a person to keep track of various things.
     */
    private static String TAB = "    ";
    private static String LINE = TAB + "____________________________________________________________";

    private static String filePath = "C:/Users/josep/duke/data/duke.txt";

    private static Storage storage;
    private static Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(ui, storage);

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

        try {
            parser.carryOutCommand(taskList);
        } catch (DukeException e) {
            ui.showDukeError(e);
        }

        ui.printByeMsg();
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
