package Task;

import Task.Task;

public class Event extends Task {
    private String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;

        super.taskType = "E";
        super.date = on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + on + ")";
    }
}
