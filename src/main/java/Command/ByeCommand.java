package Command;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMsg();
        super.isExit = true;
    }
}
