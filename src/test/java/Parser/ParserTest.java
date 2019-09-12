package Parser;

import Command.*;
import Exception.*;
import Ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParse() {
        try {
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
            assertTrue(Parser.parse("list") instanceof ListCommand);
            assertTrue(Parser.parse("done") instanceof DoneCommand);
            assertTrue(Parser.parse("delete") instanceof DeleteCommand);
            assertTrue(Parser.parse("find") instanceof FindCommand);
            assertTrue(Parser.parse("todo JUnit Test T") instanceof ToDoCommand);
            assertTrue(Parser.parse("deadline JUnit Test D /by Random DateString") instanceof DeadlineCommand);
            assertTrue(Parser.parse("event JUnit Test D /at Random DateString") instanceof EventCommand);
        } catch (DukeException e) {
            Ui ui = new Ui();
            ui.showDukeError(e);
        }
    }
    @Test
    public void shouldThrowEmptyFieldException() {
        DukeException dukeException0 = assertThrows(DukeException.class, () -> {
            Parser.parse(" ");
        });
        assertEquals(dukeException0.getExceptionType(), ExceptionType.EMPTY_FIELD);
        DukeException dukeException1 = assertThrows(DukeException.class, () -> {
            Parser.parse("");
        });
        assertEquals(dukeException1.getExceptionType(), ExceptionType.EMPTY_FIELD);
        DukeException dukeException2 = assertThrows(DukeException.class, () -> {
            Parser.parse("\n");
        });
        assertEquals(dukeException2.getExceptionType(), ExceptionType.EMPTY_FIELD);
    }
    @Test
    public void shouldThrowUnknownCommandException() {
        DukeException dukeException0 = assertThrows(DukeException.class, () -> {
            Parser.parse("Random unknown command");
        });
        assertEquals(dukeException0.getExceptionType(), ExceptionType.UNKNOWN_COMMAND);
        DukeException dukeException1 = assertThrows(DukeException.class, () -> {
           Parser.parse("Banana");
        });
    }

}
