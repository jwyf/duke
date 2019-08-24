import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String line = "\t____________________________________________________________";

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
                Task task = new Task(input);
                taskList.add(task);
                echoCommand(input);
            }
        }

        printByeMsg();
    }

    private static void printLine() {
        System.out.println(line);
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

    private static void echoCommand(String input) {
        printLine();
        System.out.println("\t added: " + input);
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
                System.out.println("\t " + (i + 1) + ".[" + currentTask.getStatusIcon() + "] "
                        + currentTask.getCommand());
            }
        }
        printLine();
    }

    private static void markAsDone (String input, ArrayList taskList) {
        //String[] words = input.split(" ");
        //Integer taskNum = Integer.parseInt(words[1]);
        try {
            Integer taskNum = Integer.parseInt(input.substring(5));
            Task currentTask = (Task) taskList.get(taskNum - 1);
            currentTask.setDoneStatus(true);
            printLine();
            System.out.println("\t Nice! I've marked this task as done: ");
            System.out.println("\t   [" + currentTask.getStatusIcon() + "] "
                    + currentTask.getCommand());
            printLine();
        }
        catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("\t Invalid task number entered! Please try again.");
            printLine();
        }
        catch (NumberFormatException e) {
            printLine();
            System.out.println("\t Invalid format entered! Please try again.");
            printLine();
        }
    }
}
