package commands;

import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;
import exceptions.ExceptionType;
import exceptions.DukeException;

import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * This method executes the ToDoCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid.
     * @throws IOException The exception thrown when the input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (input.substring(4).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_TODO);
        } else {
            ToDo todo = new ToDo(input.substring(5));
            taskList.add(todo);
            ui.printAddMsg(todo, taskList);
        }
        storage.save(taskList);
    }
}
