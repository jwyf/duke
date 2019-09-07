package Command;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ui.printByeMsg();
        super.isExit = true;
    }
}
