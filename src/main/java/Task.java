public class Task {
    protected String command;
    protected Boolean isDone;

    public Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getDoneStatus() {
        return isDone;
    }

    public void setDoneStatus(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getCommand();
    }
}
