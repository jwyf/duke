package Task;

import org.junit.jupiter.api.Test;
import task.ToDo;

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
        assertEquals(testToDo.getStatusIcon(), "✘");
        assertEquals(testToDo1.getStatusIcon(), "✘");
    }
    @Test
    public void testSetDoneStatus() {
        testToDo.setDoneStatus(true);
        testToDo1.setDoneStatus(true);
        assertEquals(testToDo.getStatusIcon(), "✓");
        assertEquals(testToDo1.getStatusIcon(), "✓");
        testToDo.setDoneStatus(false);
        testToDo1.setDoneStatus(false);
        assertEquals(testToDo.getStatusIcon(), "✘");
        assertEquals(testToDo1.getStatusIcon(), "✘");
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
        assertEquals(testToDo.toString(), "[T][✘] JUnit Test");
        assertEquals(testToDo1.toString(), "[T][✘] Random");
    }

}
