package Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Base Constructor for TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for an existing TaskList
     * @param storageList
     */
    public TaskList(TaskList storageList) {
        taskList = storageList.getTaskList();
    }

    /**
     * This getter returns the list of tasks of the TaskList
     * @return The ArrayList of tasks of the TaskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * This setter sets the list of tasks to the input TaskList
     * @param taskList The ArrayList of tasks to which the TaskList will become
     */
    public void setTaskList(ArrayList<Task> taskList) {
        taskList = taskList;
    }

    /**
     * This method adds a new Task to the TaskList
     * @param task The task to be added to the TaskList
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * This method removes an existing task from the TaskList
     * @param task The task to be removed from the TaskList
     */
    public void remove(Task task) {
        this.taskList.remove(task);
    }

    /**
     * This method gets the Task of the index from the TaskList
     * @param index The integer index of the task
     * @return The task of the integer index from the TaskList
     */
    public Task get(Integer index) {
        return taskList.get(index);
    }

    /**
     * This method returns the size of the TaskList
     * @return The integer representing the size of the TaskList
     */
    public Integer size() {
        return taskList.size();
    }

    /**
     * This method checks if the TaskList is empty
     * @return A boolean, true of the TaskList is empty, false otherwise
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

}
