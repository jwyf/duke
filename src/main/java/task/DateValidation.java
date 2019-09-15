package task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateValidation{
    /**
     * This method validates the format of the date string.
     * @param date The string representing the date.
     * @return A boolean, true of the date string is of a valid format, false otherwise.
     */
    public static boolean validateJavaDate(String date) {
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/mm/yyyy HHmm");
        simpleDateFormat.setLenient(false);
        /* Create Date object
         * parser the string into date
         */
        try {
            Date javaDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}