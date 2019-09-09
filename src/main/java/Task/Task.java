package Task;

public abstract class Task {
    protected String command;
    protected boolean isDone;
    protected String taskType;

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

    public boolean getDoneStatus() {
        return isDone;
    }
    public void setDoneStatus(boolean done) {
        isDone = done;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getCommand();
    }

}
