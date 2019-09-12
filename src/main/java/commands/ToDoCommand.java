package commands;

import Storage.Storage;
import Task.TaskList;
import Task.ToDo;
import Ui.Ui;
import exceptions.*;

import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * This method executes the ToDoCommand
     * @param taskList The list of tasks of Duke
     * @param ui The Ui, handler of Duke's responses
     * @param storage The Storage, handler of how Duke reads from and writes to its save file
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid
     * @throws IOException The exception thrown when the input is invalid
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
