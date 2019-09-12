package Task;

public abstract class Task {
    protected String command;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for classes inheriting the Task abstract class
     * @param command The string representing command of the task
     */
    public Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    /**
     * This method return the status of the Task.
     * @return If the Task is done, a tick is return, else a cross is return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This getter return the command of the Task
     * @return The string representing the command of the Task
     */
    public String getCommand() {
        return command;
    }

    /**
     * This setter changes the command of a Task
     * @param command The string of which the command of the Task will be set to
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * This getter returns the done status of the Task, true or false
     * @return A boolean, true if the Task is done, else false
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * This setter set the done status of the Task to the boolean entered
     * @param done The boolean input to which the done status of the Task will be set to
     */
    public void setDoneStatus(boolean done) {
        isDone = done;
    }

    /**
     * This getter return the task type of the Task
     * @return A string representing the task type of the Task, T for ToDo, E for Event, D for Deadline
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * This method returns a string which represents the task
     * @return A string which comprises of all the attributes of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getCommand();
    }

}
