package commands;

import exceptions.DukeException;
import exceptions.ExceptionType;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * This method executes the DeadlineCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid.
     * @throws IOException The exception thrown when the input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Deadline deadline;
        if (input.substring(8).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_DEADLINE);
        }
        String[] deadlineArray = input.substring(9).split(" /by ");
        if (deadlineArray[0].isBlank() || deadlineArray[1].isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_DEADLINE);
        }
        deadline = new Deadline(deadlineArray[0], deadlineArray[1]);
        taskList.add(deadline);
        ui.printAddMsg(deadline, taskList);
        storage.save(taskList);
    }
}
