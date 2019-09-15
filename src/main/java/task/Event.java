package task;

import java.util.Date;

public class Event extends Task {
    private String on;
    private Date date;

    /**
     * Constructor for Event.
     * @param description The description string of the Event task.
     * @param on The time string at which the Event task is held.
     */
    public Event(String description, String on) {
        super(description);
        this.on = on;

        super.taskType = "E";
    }

    /**
     * This getter returns the date string of the Event.
     * @return The string which represents the date at which the Event is held.
     */
    public String getDate() {
        return on;
    }

    /**
     * This method returns all the attributes of the Event task in a string.
     * @return The string which comprises of all the Event's attributes.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + on + ")";
    }
}
