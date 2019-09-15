package commands;

import exceptions.DukeException;
import exceptions.ExceptionType;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    /**
     * This method executes the EventCommand.
     * @param taskList The list of tasks of Duke.
     * @param ui The ui, handler of Duke's responses.
     * @param storage The Storage, handler of how Duke reads from and writes to its save file.
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid.
     * @throws IOException The exception thrown when the input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Event event;
        if (input.substring(5).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_EVENT);
        }
        String[] eventArray = input.substring(6).split(" /at ");
        if (eventArray[0].isBlank() || eventArray[1].isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_EVENT);
        }
        event = new Event(eventArray[0], eventArray[1]);
        taskList.add(event);
        ui.printAddMsg(event, taskList);
        storage.save(taskList);
    }
}
