package commands;

import Storage.Storage;
import Task.TaskList;
import exceptions.DukeException;
import Ui.Ui;

import java.io.IOException;


public abstract class Command {
    protected String input;
    protected boolean isExit;

    public Command (String input) {
        this.input = input;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException,
            IOException, IndexOutOfBoundsException, NumberFormatException;

}
