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

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        tryPopulateList(taskList); //try to load list from saved txt file
        printHelloMsg();
        carryOutCommand(scanner, taskList);
        printByeMsg();
    }

    private static void printLine() {
        System.out.println(LINE);
    }
    private static void printWithLine(String ...content) {
        printLine();
        for (String s : content) {
            System.out.println(s);
        }
        printLine();
    }

    private static void printHelloMsg() {
        printWithLine(TAB + " Hello! I'm Duke\n" + TAB + " What can I do for you?");
    }
    private static void printByeMsg() {
        printWithLine(TAB + " Bye. Hope to see you again soon!");
    }

    private static void carryOutCommand(Scanner scanner, TaskList taskList) {
        String input = "";
        while (scanner.hasNext()) {
            input = readInput(scanner);

            if (input.isBlank()) {
                System.out.println("The command cannot be blank");
            }
            else if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                iterateList(taskList);
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

    private static void echoCommand(Task task, TaskList taskList) {
        printWithLine(TAB + " Got it. I've added this task: ", TAB + "   " + task.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void iterateList(TaskList taskList) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println(TAB + " The list is currently empty, please add a new task!");
        }
        else {
            System.out.println(TAB + " Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = (Task) taskList.get(i);
                System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
            }
        }
        printLine();
    }

    private static void markDone(String input, TaskList taskList) {
        Integer taskNum = Integer.parseInt(input.substring(5));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        currentTask.setDoneStatus(true);
        printWithLine(TAB + " Nice! I've marked this task as done: ", TAB + "   ["
                + currentTask.getStatusIcon() + "] " + currentTask.getCommand());
    }
    private static void tryMarkDone(String input, TaskList taskList) {
        try {
            markDone(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            printWithLine(TAB + " Invalid task number entered! Please try again.");
        } catch (NumberFormatException e) {
            printWithLine(TAB + " Invalid format entered! Please try again.");
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
                echoCommand(todo, taskList);
                break;
            }

        } case "deadline": {
            Deadline deadline;
            String[] deadlineArray = input.substring(9).split(" /by ");
            if (input.substring(8).isBlank() || deadlineArray[1].isBlank()) {
                throw new DukeException(ErrorType.EMPTY_DEADLINE);
            }
            deadline = new Deadline(deadlineArray[0], deadlineArray[1]);
            taskList.add(deadline);
            echoCommand(deadline, taskList);
            break;

        } case "event": {
            Event event;
            String[] eventArray = input.substring(6).split(" /at ");
            if (input.substring(5).isBlank() || eventArray[1].isBlank()) {
                throw new DukeException(ErrorType.EMPTY_EVENT);
            }
            event = new Event(eventArray[0], eventArray[1]);
            taskList.add(event);
            echoCommand(event, taskList);
            break;
        }
        default: {
            throw new DukeException(ErrorType.UNKNOWN_COMMAND);
        }
        }
        saveList(taskList);
    }
    private static void tryAddTask (String input, TaskList taskList) {
        try {
            addTask(input, taskList);
        } catch (DukeException e) {
            switch (e.getErrorType()) {
            case EMPTY_TODO: {
                printLine();
                System.out.println(TAB + " ☹ OOPS!!! The description of a todo cannot be empty.");
                printLine();
                break;
            }
            case EMPTY_DEADLINE: {
                printLine();
                System.out.println(TAB + " ☹ OOPS!!! The description of a deadline cannot be empty.");
                printLine();
                break;
            }
            case EMPTY_EVENT: {
                printLine();
                System.out.println(TAB + " ☹ OOPS!!! The description of an event cannot be empty.");
                printLine();
                break;
            }
            case UNKNOWN_COMMAND: {
                printLine();
                System.out.println(TAB + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
                break;
            }
            case UNKNOWN_ERROR: {
                printLine();
                System.out.println(TAB + " ☹ OOPS!!! I'm sorry, we encountered an unknown error!");
                printLine();
                break;
            }
            }
        } catch (IOException e) {
            System.out.println("There is an IOException error: " + e.getMessage()
                    + " when writing to file");
        }
    }

    private static void deleteTask (String input, TaskList taskList) throws IOException, DukeException {
        if (input.substring(6).isBlank()) {
            throw new DukeException(ErrorType.EMPTY_DELETE);
        }
        Integer taskNum = Integer.parseInt(input.substring(7));
        Task currentTask = (Task) taskList.get(taskNum - 1);
        taskList.remove(currentTask);
        saveList(taskList);
        printWithLine(TAB + " Noted. I've removed this task: ", TAB + "   " + currentTask.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }
    private static void tryDeleteTask(String input, TaskList taskList) {
        try {
            deleteTask(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            printWithLine(TAB + " Invalid task number entered! Please try again.");
        } catch (DukeException e) {
            System.out.println(TAB + " ☹ OOPS!!! The delete field cannot be empty."); //only EMPTY_DELETE error
        } catch (NumberFormatException e) {
            printWithLine(TAB + " Invalid format entered! Please try again.");
        } catch (IOException e) {
            System.out.println("There is an IOException error: " + e.getMessage()
                    + " when writing to file");
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
        printLine();
        System.out.println(TAB + " Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            Task currentTask = (Task) findList.get(i);
            System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
        }
        printLine();
    }
    private static void tryFindTask(String input, TaskList taskList) {
        try {
            findTask(input, taskList);
        } catch (DukeException e) {
            System.out.println(TAB + " ☹ OOPS!!! The find field cannot be empty."); //only EMPTY_FIND error
        }
    }

    private static void saveList(TaskList taskList) throws IOException {
        String formattedList = new String();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = (Task) taskList.get(i);
            String taskType = currentTask.getTaskType();
            if (taskType.equals("D") || taskType.equals("E")) {
                formattedList = formattedList + currentTask.getTaskType() + " | "
                        + (currentTask.getDoneStatus() ? 1 : 0) + " | "
                        + currentTask.getCommand() + " | "
                        + currentTask.getDate()
                        + System.lineSeparator();
            }
            else {
                formattedList = formattedList + currentTask.getTaskType() + " | "
                        + (currentTask.getDoneStatus() ? 1 : 0) + " | "
                        + currentTask.getCommand()
                        + System.lineSeparator();
            }
        }
        FileWriting.writeToFile("C:/Users/josep/duke/data/duke.txt", formattedList);
    }

    private static void populateList(TaskList taskList) throws FileNotFoundException, DukeException {
        File file = new File("C:/Users/josep/duke/data/duke.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currentInput = scanner.nextLine();
            if (currentInput.startsWith("T")) {
                String[] todoArray = currentInput.split(" \\| ");
                ToDo toDo = new ToDo(todoArray[2]);
                if (todoArray[1].equals("1")) {
                    toDo.setDoneStatus(true);
                }
                else {
                    toDo.setDoneStatus(false);
                }
                taskList.add(toDo);
            }
            else if (currentInput.startsWith("D")) {
                String[] deadlineArray = currentInput.split(" \\| ", 4);
                Deadline deadline = new Deadline(deadlineArray[2], deadlineArray[3]);
                if (deadlineArray[1].equals("1")) {
                    deadline.setDoneStatus(true);
                }
                else {
                    deadline.setDoneStatus(false);
                }
                taskList.add(deadline);
            }
            else if (currentInput.startsWith("E")) {
                String[] eventArray = currentInput.split(" \\| ", 4);
                Event event = new Event(eventArray[2], eventArray[3]);
                if (eventArray[1].equals("1")) {
                    event.setDoneStatus(true);
                }
                else {
                    event.setDoneStatus(false);
                }
                taskList.add(event);
            }
            else {
                throw new DukeException(ErrorType.SAVE_CORRUPTED);
            }
        }
    }
    private static void tryPopulateList(TaskList taskList) {
        try {
            populateList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        } catch (DukeException e) {
            System.out.println(e.getErrorType()); //only file-corrupted error
            System.out.println("Your saved list is corrupted, there are unreadable entries");
        }
    }
}
