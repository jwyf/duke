package ui;

import exceptions.DukeException;
import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String TAB = "    ";
    private static String LINE = TAB + "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructor for ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * This is to show the message for the FileNotFound exception.
     */
    public void showFileNotFoundError() {
        printWithLine(TAB + " File not found exceptions, an empty list is loaded");
    }

    /**
     * This is to show the message for the IOException.
     * @param e The IOException thrown by Duke.
     */
    public void showIOExceptionError(Exception e) {
        printWithLine(TAB + " There is an IOException error: " + e.getMessage()
                + " when writing to file");
    }

    /**
     * This is to show the message for the NumberFormat exception.
     */
    public void showNumberFormatException() {
        printWithLine(TAB + " Number format exceptions! Invalid format entered!");
    }

    /**
     * This is to show the message for the IndexOutOfBounds exception.
     */
    public void showIndexOutOfBoundsException() {
        printWithLine(TAB + " Index out of Bounds exceptions! An invalid input or date was processed!");
    }

    /**
     * This is to show the messages for Duke-specific exceptions.
     * @param e The DukeException thrown by Duke if an invalid input is entered.
     */
    public void showDukeError(DukeException e) {
        String dukeErrorMsg = TAB + " Duke exceptions error: " + e.getExceptionType();
        switch (e.getExceptionType()) {
        case EMPTY_TODO: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The description of a todo cannot be empty.");
            break;
        }
        case EMPTY_DEADLINE: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The description of a deadline cannot be empty.");
            break;
        }
        case EMPTY_EVENT: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The description of an event cannot be empty.");
            break;
        }
        case UNKNOWN_COMMAND: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        case UNKNOWN_ERROR: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! I'm sorry, we encountered an unknown error!");
            break;
        }
        case SAVE_CORRUPTED: {
            printWithLine(dukeErrorMsg, TAB + " Your saved list is corrupted, there are unreadable entries",
                    TAB + " An empty list is loaded");
            break;
        }
        case EMPTY_FIELD: {
            printWithLine(dukeErrorMsg, TAB + " The field cannot be blank");
            break;
        }
        case EMPTY_DELETE: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The delete field cannot be empty.");
            break;
        }
        case EMPTY_FIND: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The find field cannot be empty.");
            break;
        }
        default: {
            printWithLine(dukeErrorMsg, TAB + " ☹ OOPS!!! The find field cannot be empty.");
        }
        }

    }

    /**
     * This is to print the formatting lines between each of Duke's responses and User's input.
     */
    private void printLine() {
        System.out.println(LINE);
    }

    /**
     * This is method is to wrap the content, Duke's responses, in formatting lines.
     * @param content All the strings that are to be wrapped by formatting lines.
     */
    private void printWithLine(String...content) {
        printLine();
        for (String s : content) {
            System.out.println(s);
        }
        printLine();
    }

    /**
     * This is to print the hello message.
     */
    public void printHelloMsg() {
        printWithLine(TAB + " Hello! I'm Duke\n" + TAB + " What can I do for you?");
    }

    /**
     * This is to print the bye message.
     */
    public void printByeMsg() {
        printWithLine(TAB + " Bye. Hope to see you again soon!");
    }

    /**
     * This is to iterate through and print out the entire TaskList provided.
     * @param taskList A list of tasks.
     */
    public void printList(TaskList taskList) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println(TAB + " The list is currently empty, please add a new task!");
        } else {
            System.out.println(TAB + " Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
            }
        }
        printLine();
    }

    /**
     * This is to print the confirmation message when a task is successfully added into Duke's save file.
     * @param task The task that is added to Duke's save file.
     * @param taskList Duke's current list of tasks.
     */
    public void printAddMsg(Task task, TaskList taskList) {
        printWithLine(TAB + " Got it. I've added this task: ", TAB + "   " + task.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * This is to print the confirmation message when a task is successfully deleted from Duke's save file.
     * @param currentTask The task that is being delete from Duke's save file.
     * @param taskList Duke's current list of tasks.
     */
    public void printDeleteMsg(Task currentTask, TaskList taskList) {
        printWithLine(TAB + " Noted. I've removed this task: ", TAB + "   " + currentTask.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * This is to print the confirmation message when a task is successfully marked as done.
     * @param currentTask The task that is being marked as done.
     */
    public void printDoneMsg(Task currentTask) {
        printWithLine(TAB + " Nice! I've marked this task as done: ", TAB + "   ["
                + currentTask.getStatusIcon() + "] " + currentTask.getCommand());
    }

    /**
     * This is to print the list of the matching tasks from Duke's task list.
     * @param findList The list of tasks matching the user's input.
     */
    public void printFindMsg(ArrayList findList) {
        printLine();
        System.out.println(TAB + " Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            Task currentTask = (Task) findList.get(i);
            System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
        }
        printLine();
    }

    /**
     * This method is to read the user's input.
     * @return The string that is the user's input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

}
