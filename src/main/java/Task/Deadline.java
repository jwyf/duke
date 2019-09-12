package Task;

import Ui.Ui;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String by;
    private LocalDateTime date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        super.taskType = "D";

        checkDate();
    }

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

    public void printDate() {
        System.out.println("Current date is " + this.date); ;
    }

    public String getDateString() {
        return by;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
