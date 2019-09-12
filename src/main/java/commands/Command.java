package commands;

import Storage.Storage;
import Task.TaskList;
import exceptions.DukeException;
import Ui.Ui;

import java.io.IOException;


public abstract class Command {
    protected String input;
    protected boolean isExit;

    /**
     * Constructor for the classes inheriting Command
     * @param input
     */
    public Command (String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * This method returns the isExit status of the Command
     * @return A boolean, true if isExit is true, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * An abstract method to be overwritten by inheriting classes
     * @param taskList The list of tasks of Duke
     * @param ui The Ui, handler of Duke's responses
     * @param storage The Storage, handler of how Duke reads from and writes to its save file
     * @throws DukeException The Duke-specific exception thrown when the user input is invalid
     * @throws IOException The exception thrown when the input is invalid
     * @throws IndexOutOfBoundsException The exception thrown when the input is invalid
     * @throws NumberFormatException The exception thrown when the input format is invalid
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException,
            IOException, IndexOutOfBoundsException, NumberFormatException;

}
