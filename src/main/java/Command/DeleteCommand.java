package Command;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (input.substring(6).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_DELETE);
        }
        Integer taskNum = Integer.parseInt(input.substring(7));
        Task currentTask = taskList.get(taskNum - 1);
        taskList.remove(currentTask);
        storage.save(taskList);
        ui.printDeleteMsg(currentTask, taskList);
    }
}
