package Command;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

public class ToDoCommand extends Command {

    ToDoCommand() {
        super("test");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

    }
}
