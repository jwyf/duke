package Command;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Integer taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        ui.printDoneMsg(currentTask);
    }
}
