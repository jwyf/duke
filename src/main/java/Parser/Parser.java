package Parser;

import commands.*;
import exceptions.*;

public class Parser {

    /**
     * This method parses the input into a Command.
     * @param input The string the user entered.
     * @return The Command that is executed next.
     * @throws DukeException This specific-to-Duke exception is thrown when the input by the user is invalid
     * @throws IndexOutOfBoundsException The exception thrown when the input is out of bounds.
     * @throws NumberFormatException The exception thrown when the input is in the wrong format.
     */
    public static Command parse(String input) throws DukeException,
            IndexOutOfBoundsException, NumberFormatException {
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
//            return null;
//        }

    }

    /**
     * This method parses a valid input into a Command, else it returns a DukeException
     * @param input The string the user entered.
     * @return A Command that is given to the parse method.
     * @throws DukeException This specific-to-Duke exception is thrown when the input by the user is invalid
     */
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
