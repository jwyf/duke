package commands;

import exceptions.DukeException;
import exceptions.ExceptionType;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    /**
     * This method executes the FindCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.substring(4).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_FIND);
        }
        String keyWord = input.substring(5);
        ArrayList<Task> findList = new ArrayList<>();
        for (Task t : taskList.getTaskList()) {
            String command = t.getCommand();
            if (command.contains(keyWord)) {
                findList.add(t);
            }
        }
        ui.printFindMsg(findList);
    }
}
