import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    /**
     * A Personal Assistant Chatbot that helps a person to keep track of various things.
     */
    private static String LINE = "\t____________________________________________________________";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String input = null;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            populateList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        } catch (DukeException e) {
            System.out.println(e.getErrorType());
        }

        printHelloMsg();

        while (scanner.hasNext()) {
            input = readInput(scanner);

            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                iterateList(taskList);
            }
            else if (input.startsWith("done ")) {
                markAsDone(input, taskList);
            }

            else {
                try {
                    addTask(input, taskList);
                } catch (DukeException e) {
                    switch (e.getErrorType()) {
                        case EMPTY_TODO: {
                            printLine();
                            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
                            printLine();
                            break;
                        }
                        case EMPTY_DEADLINE: {
                            printLine();
                            System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be empty.");
                            printLine();
                            break;
                        }
                        case EMPTY_EVENT: {
                            printLine();
                            System.out.println("\t ☹ OOPS!!! The description of an event cannot be empty.");
                            printLine();
                            break;
                        }
                        case UNKNOWN_COMMAND: {
                            printLine();
                            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            printLine();
                            break;
                        }
                        case UNKNOWN_ERROR: {
                            printLine();
                            System.out.println("\t ☹ OOPS!!! I'm sorry, we encountered an unknown error!");
                            printLine();
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("There is an IOException error: " + e.getMessage()
                            + " when writing to file");
                }
            }
        }

        printByeMsg();
    }

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void printHelloMsg() {
        printLine();
        System.out.println("\t Hello! I'm Duke\n" + "\t What can I do for you?");
        printLine();
    }

    private static void printByeMsg() {
        printLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        printLine();
    }

    private static String readInput(Scanner scanner) {
        String commands = scanner.nextLine();
        return commands;
    }

    private static void echoCommand(Task task, ArrayList taskList) {
        printLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        printLine();
    }

    private static void iterateList(ArrayList taskList) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println("\t The list is currently empty, please add a new task!");
        }
        else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = (Task) taskList.get(i);
                System.out.println("\t " + (i + 1) + ". " + currentTask.toString());
            }
        }
        printLine();
    }

    private static void markAsDone(String input, ArrayList taskList) {
        try {
            Integer taskNum = Integer.parseInt(input.substring(5));
            Task currentTask = (Task) taskList.get(taskNum - 1);
            currentTask.setDoneStatus(true);
            printLine();
            System.out.println("\t Nice! I've marked this task as done: ");
            System.out.println("\t   [" + currentTask.getStatusIcon() + "] "
                    + currentTask.getCommand());
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("\t Invalid task number entered! Please try again.");
            printLine();
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("\t Invalid format entered! Please try again.");
            printLine();
        }
    }

    private static void addTask (String input, ArrayList taskList) throws DukeException, IOException {
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
                if (input.substring(8).isBlank()) {
                    throw new DukeException(ErrorType.EMPTY_DEADLINE);
                }
                String[] deadlineArray = input.substring(9).split(" /by ");
                deadline = new Deadline(deadlineArray[0], deadlineArray[1]);
                taskList.add(deadline);
                echoCommand(deadline, taskList);
                break;

            } case "event": {
                Event event;
                if (input.substring(5).isBlank()) {
                    throw new DukeException(ErrorType.EMPTY_EVENT);
                }
                String[] eventArray = input.substring(6).split(" /at ");
                event = new Event(eventArray[0], eventArray[1]);
                taskList.add(event);
                echoCommand(event, taskList);
                break;
            }
            default: {
                throw new DukeException(ErrorType.UNKNOWN_COMMAND);
//                Task task = new Task(input);
//                taskList.add(task);
//                echoCommand(task, taskList);
            }
        }
        saveList(taskList);
    }

    private static void saveList(ArrayList taskList) throws IOException {
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

    private static void populateList (ArrayList taskList) throws FileNotFoundException, DukeException {
        File file = new File("C:/Users/josep/duke/data/duke.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currentInput = scanner.nextLine();
            if (currentInput.startsWith("T")) {
//                String noSpace = currentInput.replaceAll("\\s+","");
//                String processedInput = noSpace.replaceAll("|"," ");
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
}
