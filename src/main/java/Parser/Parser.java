package Parser;

import Command.*;
import Task.*;
import Exception.*;

import java.io.IOException;


public class Parser {

    public static Command parse(String input) throws DukeException,
            IOException, IndexOutOfBoundsException, NumberFormatException {
        if (input.isBlank()) {
            throw new DukeException(ExceptionType.EMPTY_FIELD);
        }
        else if (input.equals("bye")) {
            return new ByeCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("done")) {
            return new DoneCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            return addTask(input);
        }
//        else if (input.startsWith("print date")) {
//            Task.Deadline deadline = (Task.Deadline) taskList.get(5);
//            deadline.printDate();
//        }
    }

    private static Command addTask(String input) throws DukeException {
        String[] words = input.split(" ");
        String taskType = words[0];
        switch (taskType) {
        case "todo": {
            return new ToDoCommand(input);
        }
        case "deadline": {
            return new DeadlineCommand(input);
        }
        case "event": {
            return new EventCommand(input);
        }
        default: {
            throw new DukeException(ExceptionType.UNKNOWN_COMMAND);
        }
        }
    }
}
