import java.util.Date;

public class Deadline extends Task {
    protected String by;
    protected Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        super.taskType = "D";
        super.date = by;
        checkDate();
    }

    public void checkDate() {
        String[] byArray = by.split(" ");
        String date = byArray[0];
        String time = byArray[1];
        if (DateValidation.validateJavaDate(date) == true) {
            String[] dateArray = date.split("/");
            this.date = new Date(Integer.parseInt(dateArray[2]) - 1901,
                    Integer.parseInt(dateArray[1]),
                    Integer.parseInt(dateArray[0]),
                    Integer.parseInt(time.substring(0, 2)),
                    Integer.parseInt(time.substring(3, 4)));
        }
    }

    public void printDate() {
        System.out.println("Current date is " + date); ;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
