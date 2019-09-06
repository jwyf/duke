import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    protected Ui ui;
    protected Storage storage;

//    public static /*Command*/ String parse(String fullCommand) {
//        return fullCommand;
//    }

    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    public void carryOutCommand(Scanner scanner, TaskList taskList) throws DukeException {
        String input = "";
        while (scanner.hasNext()) {
            input = readInput(scanner);

            if (input.isBlank()) {
                throw new DukeException(ErrorType.EMPTY_FIELD);
            }
            else if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                ui.printList(taskList);
            } else if (input.startsWith("done")) {
                tryMarkDone(input, taskList);
            } else if (input.startsWith("delete")) {
                tryDeleteTask(input, taskList);
            } else if (input.startsWith("find")) {
                tryFindTask(input, taskList);
            } else {
                tryAddTask(input, taskList);
            }
//            else if (input.startsWith("print date")) {
//                Deadline deadline = (Deadline) taskList.get(5);
//                deadline.printDate();
//            }
        }
    }

    private String readInput(Scanner scanner) {
        String commands = scanner.nextLine();
        return commands;
    }

    private void markDone(String input, TaskList taskList) {
        Integer taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        ui.printDoneMsg(currentTask);
    }
    private void tryMarkDone(String input, TaskList taskList) {
        try {
            markDone(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            ui.showNumberFormatException();
        }
    }

    private void addTask (String input, TaskList taskList) throws DukeException, IOException {
        String[] words = input.split(" ");
        String taskType = words[0];
        switch (taskType) {
        case "todo": {
            if (input.substring(4).isBlank()) {
                throw new DukeException(ErrorType.EMPTY_TODO);
            } else {
                ToDo todo = new ToDo(input.substring(5));
                taskList.add(todo);
                ui.printAddMsg(todo, taskList);
                break;
            }

        } case "deadline": {
            Deadline deadline;
            if (input.substring(8).isBlank()) {
                throw new DukeException(ErrorType.EMPTY_DEADLINE);
            }
            String[] deadlineArray = input.substring(9).split(" /by ");
            if (deadlineArray[1].isBlank()) {
                throw new DukeException(ErrorType.EMPTY_DEADLINE);
            }
            deadline = new Deadline(deadlineArray[0], deadlineArray[1]);
            taskList.add(deadline);
            ui.printAddMsg(deadline, taskList);
            break;

        } case "event": {
            Event event;
            if (input.substring(5).isBlank()) {
                throw new DukeException(ErrorType.EMPTY_EVENT);
            }
            String[] eventArray = input.substring(6).split(" /at ");
            if (eventArray[1].isBlank()) {
                throw new DukeException(ErrorType.EMPTY_EVENT);
            }
            event = new Event(eventArray[0], eventArray[1]);
            taskList.add(event);
            ui.printAddMsg(event, taskList);
            break;
        }
        default: {
            throw new DukeException(ErrorType.UNKNOWN_COMMAND);
        }
        }
        storage.save(taskList);
    }
    private void tryAddTask (String input, TaskList taskList) {
        try {
            addTask(input, taskList);
        } catch (DukeException e) {
            ui.showDukeError(e);
        } catch (IOException e) {
            ui.showIOExceptionError(e);
        }
    }

    private void deleteTask (String input, TaskList taskList) throws IOException, DukeException {
        if (input.substring(6).isBlank()) {
            throw new DukeException(ErrorType.EMPTY_DELETE);
        }
        Integer taskNum = Integer.parseInt(input.substring(7));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        taskList.remove(currentTask);
        storage.save(taskList);
        ui.printDeleteMsg(currentTask, taskList);
    }
    private void tryDeleteTask(String input, TaskList taskList) {
        try {
            deleteTask(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundsException();
        } catch (DukeException e) {
            ui.showDukeError(e); //only empty delete error
        } catch (NumberFormatException e) {
            ui.showNumberFormatException();
        } catch (IOException e) {
            ui.showIOExceptionError(e);
        }
    }

    private void findTask(String input, TaskList taskList) throws DukeException {
        if (input.substring(4).isBlank()) {
            throw new DukeException(ErrorType.EMPTY_FIND);
        }
        String keyWord = input.substring(5);
        ArrayList<Task> findList = new ArrayList<>();
        for (Task t : taskList.getTaskList()) {
            String command = t.getCommand();
            if (command.contains(keyWord)) {
                findList.add(t);
            }
        }
        ui.printFindMsg(findList);
    }
    private void tryFindTask(String input, TaskList taskList) {
        try {
            findTask(input, taskList);
        } catch (DukeException e) {
            ui.showDukeError(e); //only EMPTY_FIND error
        }
    }
}
