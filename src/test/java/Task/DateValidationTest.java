package Task;

import org.junit.jupiter.api.Test;
import task.DateValidation;

import static org.junit.jupiter.api.Assertions.*;

public class DateValidationTest {
    @Test
    public void testValidateJavaDate() {
        assertTrue(DateValidation.validateJavaDate("01/01/2019 1200"));
        assertTrue(DateValidation.validateJavaDate("2/12/2019 1800"));

        assertFalse(DateValidation.validateJavaDate(""));
        assertFalse(DateValidation.validateJavaDate(" "));
        assertFalse(DateValidation.validateJavaDate("Random string"));
        assertFalse(DateValidation.validateJavaDate("OneWordString"));

        assertFalse(DateValidation.validateJavaDate("01012019 1200"));
        assertFalse(DateValidation.validateJavaDate("01/01/2019 12:00"));
        assertFalse(DateValidation.validateJavaDate("01-01-2019 1200"));
        assertFalse(DateValidation.validateJavaDate("01.01.2019 1200"));
        assertFalse(DateValidation.validateJavaDate("00/00/2019 1200"));
    }
}
