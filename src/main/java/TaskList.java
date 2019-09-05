import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(TaskList storageList) {
        taskList = storageList.getTaskList();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        taskList = taskList;
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

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

}
