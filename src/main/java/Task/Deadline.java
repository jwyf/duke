package Task;

import java.util.Date;

public class Deadline extends Task {
    private String by;
    private Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        super.taskType = "D";
        checkDate();
    }

    public void checkDate() {
        try {
            String[] byArray = by.split(" ");
            String date = byArray[0];
            String time = byArray[1];
            if (DateValidation.validateJavaDate(by) == true) {
                String[] dateArray = date.split("/");
                Integer year = Integer.parseInt(dateArray[2]) - 1900;
                Integer month = Integer.parseInt(dateArray[1]) - 1;
                Integer day = Integer.parseInt(dateArray[0]);
                Integer hours = Integer.parseInt(time.substring(0, 2));
                Integer minutes = Integer.parseInt(time.substring(3, 4));
                this.date = new Date(year, month, day, hours, minutes);
            }
            else {
                this.date = null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("     This date cannot be converted.");
        }
    }

    public void printDate() {
        System.out.println("Current date is " + this.date); ;
    }

    public String getDate() {
        return by;
//        if (date == null) {
//            return dateString;
//        }
//        else {
//            return date.toString();
//        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
