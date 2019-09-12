package Storage;

import Exception.*;
import Task.*;
import Ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    String trueFilePath = "C:/Users/josep/duke/data/duke.txt";
    String corruptedFilePath = "C:/Users/josep/duke/data/corruptedDuke.txt";
    String fakeFilePath = "C:/Users/josep/duke/data/fakeFile.txt";
    String testFilePath = "C:/Users/josep/duke/data/testDuke.txt";

    Ui ui = new Ui();
    @Test
    public void testLoad() {
        try {
            Storage storage = new Storage(trueFilePath);
            TaskList testTaskList = storage.load();

            TaskList expectedTaskList = new TaskList();
            ToDo readBookTodo = new ToDo("read book");
            readBookTodo.setDoneStatus(true);
            expectedTaskList.add(readBookTodo);

            Deadline returnBookDeadline = new Deadline("return book", "June 6th");
            expectedTaskList.add(returnBookDeadline);

            Event projectMeetingEvent = new Event("project meeting", "Aug 6th 2-4pm");
            expectedTaskList.add(projectMeetingEvent);

            ToDo joinSportsClubToDo = new ToDo("join sports club");
            joinSportsClubToDo.setDoneStatus(true);
            expectedTaskList.add(joinSportsClubToDo);

            ToDo addToDo = new ToDo("add");
            expectedTaskList.add(addToDo);

            Deadline testPrintDeadline = new Deadline("test print", "2/12/2019 1800");
            testPrintDeadline.setDoneStatus(true);
            expectedTaskList.add(testPrintDeadline);

            assertEquals(testTaskList.size(), expectedTaskList.size());
            for (int i = 0; i < testTaskList.size(); i++) {
                assertEquals(testTaskList.get(i).toString(), expectedTaskList.get(i).toString());
            }
        } catch(FileNotFoundException e) {
            ui.showFileNotFoundError();
        } catch (DukeException e) {
            ui.showDukeError(e);
        }
    }

    @Test
    public void shouldThrowFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> {
            Storage storage = new Storage("");
            TaskList taskList = storage.load();
        });

        assertThrows(FileNotFoundException.class, () -> {
            Storage storage = new Storage(fakeFilePath);
            TaskList taskList = storage.load();
        });
    }

    @Test
    public void shouldThrowDukeException() {
        DukeException dukeException = assertThrows(DukeException.class, () -> {
            Storage storage = new Storage(corruptedFilePath); //corrupted txt file
            TaskList taskList = storage.load();
        });
        assertEquals(dukeException.getExceptionType(), ExceptionType.SAVE_CORRUPTED);
    }

    @Test
    public void testSave() {
        try {
            Storage storage = new Storage(testFilePath);
            TaskList testTaskList = new TaskList();
            storage.save(testTaskList); //to clear the save file first

            ToDo randomToDo = new ToDo("random");
            testTaskList.add(randomToDo);
            storage.save(testTaskList);

            TaskList outputTaskList = storage.load();

            assertEquals(testTaskList.size(), outputTaskList.size());
            for (int i = 0; i < testTaskList.size(); i++) {
                assertEquals(testTaskList.get(i).toString(), outputTaskList.get(i).toString());
            }
        } catch (IOException e) {
            ui.showIOExceptionError(e);
        } catch (DukeException e) {
            ui.showDukeError(e);
        }

    }

    @Test
    public void shouldThrowIOException() {
        assertThrows(IOException.class, () -> {
            Storage storage = new Storage("");
            TaskList taskList = new TaskList();
            storage.save(taskList);
        });
    }
}
