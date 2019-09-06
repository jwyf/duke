package Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateValidation{
    public static boolean validateJavaDate(String date)
    {
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/mm/yyyy HHmm");
        simpleDateFormat.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try
        {
            Date javaDate = simpleDateFormat.parse(date);
//            System.out.println(date + " is valid date format");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
//            System.out.println(date + " is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }
//    public static void main(String args[]){
//        validateJavaDate("2/12/2019 1800");
//        validateJavaDate("12-29-2016");
//        validateJavaDate("12,29,2016");
//        validateJavaDate("");
//    }
}