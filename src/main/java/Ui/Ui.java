package Ui;

import Task.*;
import Exception.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String TAB = "    ";
    private static String LINE = TAB + "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showFileNotFoundError() {
        printWithLine(TAB + " File not found exception, an empty list is loaded");
    }
    public void showIOExceptionError(Exception e) {
        printWithLine(TAB + " There is an IOException error: " + e.getMessage()
                + " when writing to file");
    }
    public void showNumberFormatException() {
        printWithLine(TAB + " Number format exception! Invalid format entered!");
    }
    public void showIndexOutOfBoundsException() {
        printWithLine(TAB + " Index out of Bounds exception! An invalid number or input was entered!");
    }

    public void showDukeError(DukeException e) {
        String dukeErrorMsg = TAB + " Duke exception error: " + e.getExceptionType();
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
        }

    }

    private void printLine() {
        System.out.println(LINE);
    }
    private void printWithLine(String ...content) {
        printLine();
        for (String s : content) {
            System.out.println(s);
        }
        printLine();
    }

    public void printHelloMsg() {
        printWithLine(TAB + " Hello! I'm Duke\n" + TAB + " What can I do for you?");
    }
    public void printByeMsg() {
        printWithLine(TAB + " Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println(TAB + " The list is currently empty, please add a new task!");
        }
        else {
            System.out.println(TAB + " Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
            }
        }
        printLine();
    }

    public void printAddMsg(Task task, TaskList taskList) {
        printWithLine(TAB + " Got it. I've added this task: ", TAB + "   " + task.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }
    public void printDeleteMsg(Task currentTask, TaskList taskList) {
        printWithLine(TAB + " Noted. I've removed this task: ", TAB + "   " + currentTask.toString(),
                TAB + " Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDoneMsg(Task currentTask) {
        printWithLine(TAB + " Nice! I've marked this task as done: ", TAB + "   ["
                + currentTask.getStatusIcon() + "] " + currentTask.getCommand());
    }

    public void printFindMsg(ArrayList findList) {
        printLine();
        System.out.println(TAB + " Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            Task currentTask = (Task) findList.get(i);
            System.out.println(TAB + " " + (i + 1) + ". " + currentTask.toString());
        }
        printLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
