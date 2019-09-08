package Task;

import java.util.Date;

public class Event extends Task {
    private String on;
    private Date date;

    public Event(String description, String on) {
        super(description);
        this.on = on;

        super.taskType = "E";
    }

    public String getDate() {
        return on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + on + ")";
    }
}
