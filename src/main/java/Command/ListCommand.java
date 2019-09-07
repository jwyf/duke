package Command;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
