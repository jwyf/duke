package storage;

import exceptions.DukeException;
import exceptions.ExceptionType;
import task.TaskList;
import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private TaskList taskList;

    /**
     * Constructor of Storage.
     * @param filePath A string representing the file path to Duke's save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new TaskList();
    }

    /**
     * This method loads the text save file of Duke and returns the data of it.
     * @return The TaskList from previous saves of Duke.
     * @throws FileNotFoundException This exception is thrown when the save file is not found.
     * @throws DukeException This Duke-specific exception is thrown when the user input is invalid.
     */
    public TaskList load() throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String currentInput = scanner.nextLine();
            if (currentInput.startsWith("T")) {
                String[] todoArray = currentInput.split(" \\| ");
                ToDo toDo = new ToDo(todoArray[2]);
                if (todoArray[1].equals("1")) {
                    toDo.setDoneStatus(true);
                } else {
                    toDo.setDoneStatus(false);
                }
                taskList.add(toDo);
            } else if (currentInput.startsWith("D")) {
                String[] deadlineArray = currentInput.split(" \\| ", 4);
                Deadline deadline = new Deadline(deadlineArray[2], deadlineArray[3]);
                if (deadlineArray[1].equals("1")) {
                    deadline.setDoneStatus(true);
                } else {
                    deadline.setDoneStatus(false);
                }
                taskList.add(deadline);
            } else if (currentInput.startsWith("E")) {
                String[] eventArray = currentInput.split(" \\| ", 4);
                Event event = new Event(eventArray[2], eventArray[3]);
                if (eventArray[1].equals("1")) {
                    event.setDoneStatus(true);
                } else {
                    event.setDoneStatus(false);
                }
                taskList.add(event);
            } else {
                throw new DukeException(ExceptionType.SAVE_CORRUPTED);
            }
        }
        return taskList;
    }

    /**
     * This method writes to the text save file of Duke.
     * @param modifiedList The TaskList to be saved, written into the text file.
     * @throws IOException The exception thrown when the file is not found.
     */
    public void save(TaskList modifiedList) throws IOException {
        taskList = modifiedList;
        String formattedList = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String taskType = currentTask.getTaskType();

            if (taskType.equals("D")) {
                Deadline deadline = (Deadline) currentTask;
                formattedList = formattedList + deadline.getTaskType() + " | "
                        + (deadline.getDoneStatus() ? 1 : 0) + " | "
                        + deadline.getCommand() + " | "
                        + deadline.getDateString()
                        + System.lineSeparator();
            } else if (taskType.equals("E")) {
                Event event = (Event) currentTask;
                formattedList = formattedList + event.getTaskType() + " | "
                        + (event.getDoneStatus() ? 1 : 0) + " | "
                        + event.getCommand() + " | "
                        + event.getDate()
                        + System.lineSeparator();
            } else {
                formattedList = formattedList + currentTask.getTaskType() + " | "
                        + (currentTask.getDoneStatus() ? 1 : 0) + " | "
                        + currentTask.getCommand()
                        + System.lineSeparator();
            }
        }
        FileWriting.writeToFile(filePath, formattedList);
    }
}
