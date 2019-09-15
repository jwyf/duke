package Task;

import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline testDeadline = new Deadline("JUnit Test", "Test dateString");
    private Deadline testDeadline1 = new Deadline("Random", "07/10/2019 2100");
    @Test
    public void testGetDoneStatus() {
        assertEquals(testDeadline.getDoneStatus(), false);
        assertEquals(testDeadline1.getDoneStatus(), false);
    }
    @Test
    public void testGetStatusIcon() {
        assertEquals(testDeadline.getStatusIcon(), "✘");
        assertEquals(testDeadline1.getStatusIcon(), "✘");
    }
    @Test
    public void testSetDoneStatus() {
        testDeadline.setDoneStatus(true);
        testDeadline1.setDoneStatus(true);
        assertEquals(testDeadline.getStatusIcon(), "✓");
        assertEquals(testDeadline1.getStatusIcon(), "✓");
        testDeadline.setDoneStatus(false);
        testDeadline1.setDoneStatus(false);
        assertEquals(testDeadline.getStatusIcon(), "✘");
        assertEquals(testDeadline1.getStatusIcon(), "✘");
    }

    @Test
    public void testGetCommand() {
        assertEquals(testDeadline.getCommand(), "JUnit Test");
        assertEquals(testDeadline1.getCommand(), "Random");
    }
    @Test
    public void testSetCommand() {
        testDeadline.setCommand("Random test string");
        assertEquals(testDeadline.getCommand(), "Random test string");
        testDeadline1.setCommand("OneLongWordStringTest");
        assertEquals(testDeadline1.getCommand(), "OneLongWordStringTest");
    }
    @Test
    public void testGetTaskType() {
        assertEquals(testDeadline.getTaskType(), "D");
        assertEquals(testDeadline1.getTaskType(), "D");
    }

    @Test
    public void testGetDateString() {
        assertEquals(testDeadline.getDateString(), "Test dateString");
        assertEquals(testDeadline1.getDateString(), "07/10/2019 2100");
    }

    @Test
    public void testGetDate() {
        assertEquals(testDeadline.getDate(), null);
        assertEquals(testDeadline1.getDate(), LocalDateTime.of(2019, 10, 7, 21, 0));
    }

    @Test
    public void testToString() {
        assertEquals(testDeadline.toString(), "[D][✘] JUnit Test (by: Test dateString)");
        assertEquals(testDeadline1.toString(), "[D][✘] Random (by: 07/10/2019 2100)");
    }

}
