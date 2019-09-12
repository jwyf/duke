package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private ToDo testToDo = new ToDo("JUnit Test");
    private ToDo testToDo1 = new ToDo("Random");
    @Test
    public void testGetDoneStatus() {
        assertEquals(testToDo.getDoneStatus(), false);
        assertEquals(testToDo1.getDoneStatus(), false);
    }
    @Test
    public void testGetStatusIcon() {
        assertEquals(testToDo.getStatusIcon(), "\u2718");
        assertEquals(testToDo1.getStatusIcon(), "\u2718");
    }
    @Test
    public void testSetDoneStatus() {
        testToDo.setDoneStatus(true);
        testToDo1.setDoneStatus(true);
        assertEquals(testToDo.getStatusIcon(), "\u2713");
        assertEquals(testToDo1.getStatusIcon(), "\u2713");
        testToDo.setDoneStatus(false);
        testToDo1.setDoneStatus(false);
        assertEquals(testToDo.getStatusIcon(), "\u2718");
        assertEquals(testToDo1.getStatusIcon(), "\u2718");
    }

    @Test
    public void testGetCommand() {
        assertEquals(testToDo.getCommand(), "JUnit Test");
        assertEquals(testToDo1.getCommand(), "Random");
    }
    @Test
    public void testSetCommand() {
        testToDo.setCommand("Random test string");
        assertEquals(testToDo.getCommand(), "Random test string");
        testToDo1.setCommand("OneLongWordStringTest");
        assertEquals(testToDo1.getCommand(), "OneLongWordStringTest");
    }
    @Test
    public void testGetTaskType() {
        assertEquals(testToDo.getTaskType(), "T");
        assertEquals(testToDo1.getTaskType(), "T");
    }

    @Test
    public void testToString() {
        assertEquals(testToDo.toString(), "[T][\u2718] JUnit Test");
        assertEquals(testToDo1.toString(), "[T][\u2718] Random");
    }

}
