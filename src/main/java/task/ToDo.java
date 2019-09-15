package task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        super.taskType = "T";
    }

    /**
     * This method returns all the attributes of the ToDo task in a string.
     * @return The string which comprises of all the ToDo's attributes.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
