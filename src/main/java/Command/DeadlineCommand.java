package Command;

import Exception.DukeException;
import Exception.ExceptionType;
import Storage.Storage;
import Task.Deadline;
import Task.TaskList;
import Ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

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
