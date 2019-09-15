package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    /**
     * This method executes the DoneCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     * @throws IndexOutOfBoundsException The exception thrown when the input is invalid.
     * @throws NumberFormatException The exception thrown when the input format is invalid.
     * @throws IOException The exception thrown when the input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException,
            NumberFormatException, IOException {
        int taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        ui.printDoneMsg(currentTask);
        storage.save(taskList);
    }
}
