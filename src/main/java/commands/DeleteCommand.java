package commands;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import Ui.Ui;
import exceptions.*;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * This method executes the DeleteCommand
     * @param taskList The list of tasks of Duke
     * @param ui The Ui, handler of Duke's responses
     * @param storage The Storage, handler of how Duke reads from and writes to its save file
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid
     * @throws IOException The exception thrown when the input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (input.substring(6).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_DELETE);
        }
        int taskNum = Integer.parseInt(input.substring(7));
        Task currentTask = taskList.get(taskNum - 1);
        taskList.remove(currentTask);
        storage.save(taskList);
        ui.printDeleteMsg(currentTask, taskList);
    }
}
