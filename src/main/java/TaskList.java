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
        this.taskList.add(task);
    }
    public void remove(Task task) {
        this.taskList.remove(task);
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
