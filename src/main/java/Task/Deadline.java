package Task;

import Ui.Ui;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String by;
    private LocalDateTime date;

    /**
     * Constructor for Deadline
     * @param description The description string of the Deadline task
     * @param by The time string by which the Deadline task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        super.taskType = "D";

        checkDate();
    }

    /**
     * This method checks if the time string of the Deadline is valid to be converted to LocalDateTime
     */
    private void checkDate() {
        try {
            String[] byArray = by.split(" ");
            String date = byArray[0];
            String time = byArray[1];
            if (DateValidation.validateJavaDate(by) == true) {
                String[] dateArray = date.split("/");

                Integer year = Integer.parseInt(dateArray[2]);
                Integer month = Integer.parseInt(dateArray[1]);
                Integer day = Integer.parseInt(dateArray[0]);
                Integer hours = Integer.parseInt(time.substring(0, 2));
                Integer minutes = Integer.parseInt(time.substring(3, 4));
                this.date = LocalDateTime.of(year, month, day, hours, minutes);
            } else {
                this.date = null;
            }
        } catch (IndexOutOfBoundsException e) {
            Ui ui = new Ui();
            ui.showIndexOutOfBoundsException();
        }
    }

    /**
     * This getter returns the date string of the Deadline
     * @return The string which represents the date the deadline is due
     */
    public String getDateString() {
        return by;
    }

    /**
     * This getter returns the LocalDateTime of the Deadline
     * @return The LocalDateTime object which represents the date the deadline is due
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * This method returns all the attributes of the Deadline task in a string
     * @return The string which comprises of all the Deadline's attributes
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
