package Command;

import Storage.Storage;
import Task.TaskList;
import Task.ToDo;
import Ui.Ui;
import Exception.*;

import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String input) {
        super(input);
    }

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
