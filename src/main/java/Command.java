import Task.TaskList;

public abstract class Command {
    protected String command;
    protected boolean isExit;

    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return isExit;
    }
}
