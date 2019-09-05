import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> storageList) {
        Collections.copy(taskList, storageList);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }
    public void remove(Integer index) {
        taskList.remove(index);
    }

    public Task get(Integer index) {
        return taskList.get(index);
    }

    public Integer size() {
        return taskList.size();
    }

    public Boolean isEmpty() {
        return taskList.isEmpty();
    }

}
