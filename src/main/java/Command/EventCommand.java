package Command;

import Exception.DukeException;
import Exception.ExceptionType;
import Storage.Storage;
import Task.Event;
import Task.TaskList;
import Ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Event event;
        if (input.substring(5).isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_EVENT);
        }
        String[] eventArray = input.substring(6).split(" /at ");
        if (eventArray[1].isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_EVENT);
        }
        event = new Event(eventArray[0], eventArray[1]);
        taskList.add(event);
        ui.printAddMsg(event, taskList);
        storage.save(taskList);
    }
}
