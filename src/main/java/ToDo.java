public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        super.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
