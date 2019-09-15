package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String input) {
        super(input);
    }

    /**
     * This method executes the ByeCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMsg();
        super.isExit = true;
    }
}
