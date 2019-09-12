package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event testEvent = new Event("JUnit Test", "Test dateString");
    private Event testEvent1 = new Event("Random", "07/10/2019 2100");
    @Test
    public void testGetDoneStatus() {
        assertEquals(testEvent.getDoneStatus(), false);
        assertEquals(testEvent1.getDoneStatus(), false);
    }
    @Test
    public void testGetStatusIcon() {
        assertEquals(testEvent.getStatusIcon(), "\u2718");
        assertEquals(testEvent1.getStatusIcon(), "\u2718");
    }
    @Test
    public void testSetDoneStatus() {
        testEvent.setDoneStatus(true);
        testEvent1.setDoneStatus(true);
        assertEquals(testEvent.getStatusIcon(), "\u2713");
        assertEquals(testEvent1.getStatusIcon(), "\u2713");
        testEvent.setDoneStatus(false);
        testEvent1.setDoneStatus(false);
        assertEquals(testEvent.getStatusIcon(), "\u2718");
        assertEquals(testEvent1.getStatusIcon(), "\u2718");
    }

    @Test
    public void testGetCommand() {
        assertEquals(testEvent.getCommand(), "JUnit Test");
        assertEquals(testEvent1.getCommand(), "Random");
    }
    @Test
    public void testSetCommand() {
        testEvent.setCommand("Random test string");
        assertEquals(testEvent.getCommand(), "Random test string");
        testEvent1.setCommand("OneLongWordStringTest");
        assertEquals(testEvent1.getCommand(), "OneLongWordStringTest");
    }
    @Test
    public void testGetTaskType() {
        assertEquals(testEvent.getTaskType(), "E");
        assertEquals(testEvent1.getTaskType(), "E");
    }

    @Test
    public void testGetDate() {
        assertEquals(testEvent.getDate(), "Test dateString");
        assertEquals(testEvent1.getDate(), "07/10/2019 2100");
    }

    @Test
    public void testToString() {
        assertEquals(testEvent.toString(), "[E][\u2718] JUnit Test (at: Test dateString)");
        assertEquals(testEvent1.toString(), "[E][\u2718] Random (at: 07/10/2019 2100)");
    }

}
