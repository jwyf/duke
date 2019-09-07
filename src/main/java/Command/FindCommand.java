package Command;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import Ui.Ui;
import Exception.*;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
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
