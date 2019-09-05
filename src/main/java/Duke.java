import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    /**
     * A Personal Assistant Chatbot that helps a person to keep track of various things.
     */
    private static String TAB = "    ";
    private static String LINE = TAB + "____________________________________________________________";

    private static String filePath = "C:/Users/josep/duke/data/duke.txt";

    private static Storage storage;
    private TaskList taskList;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.showDukeError(e); //save corrupted exception
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.printHelloMsg();

        try {
            carryOutCommand(scanner, taskList);
        } catch (DukeException e) {
            ui.showDukeError(e);
        }

        ui.printByeMsg();
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }


    private static void carryOutCommand(Scanner scanner, TaskList taskList) throws DukeException {
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

    private static String readInput(Scanner scanner) {
        String commands = scanner.nextLine();
        return commands;
    }

    private static void markDone(String input, TaskList taskList) {
        Integer taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        ui.printDoneMsg(currentTask);
    }
    private static void tryMarkDone(String input, TaskList taskList) {
        try {
            markDone(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            ui.showNumberFormatException();
        }
    }

    private static void addTask (String input, TaskList taskList) throws DukeException, IOException {
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
    private static void tryAddTask (String input, TaskList taskList) {
        try {
            addTask(input, taskList);
        } catch (DukeException e) {
            ui.showDukeError(e);
        } catch (IOException e) {
            ui.showIOExceptionError(e);
        }
    }

    private static void deleteTask (String input, TaskList taskList) throws IOException, DukeException {
        if (input.substring(6).isBlank()) {
            throw new DukeException(ErrorType.EMPTY_DELETE);
        }
        Integer taskNum = Integer.parseInt(input.substring(7));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        taskList.remove(currentTask);
        storage.save(taskList);
        ui.printDeleteMsg(currentTask, taskList);
    }
    private static void tryDeleteTask(String input, TaskList taskList) {
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

    private static void findTask(String input, TaskList taskList) throws DukeException {
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
    private static void tryFindTask(String input, TaskList taskList) {
        try {
            findTask(input, taskList);
        } catch (DukeException e) {
            ui.showDukeError(e); //only EMPTY_FIND error
        }
    }
}
