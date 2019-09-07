package Storage;

import Task.*;
import Exception.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String filePath;
    TaskList taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new TaskList();
    }

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
                throw new DukeException(ExceptionType.SAVE_CORRUPTED);
            }
        }
        return taskList;
    }

    public void save(TaskList modifiedList) throws IOException {
        taskList = modifiedList;
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
        FileWriting.writeToFile(filePath, formattedList);
    }
}
