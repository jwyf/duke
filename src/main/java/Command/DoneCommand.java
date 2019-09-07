package Command;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import Ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException, NumberFormatException, IOException {
        int taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        ui.printDoneMsg(currentTask);
        storage.save(taskList);
    }
}
