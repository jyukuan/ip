import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks and provides operations for task manipulation.
 */
public class TaskList {
    /** The list containing all tasks. */
    private final List<Task> tasks;

    /**
     * Constructs a TaskList with optional initial tasks.
     *
     * @param tasks Initial list of tasks (can be null).
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Deletes a task from the list by index.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed task.
     * @throws DukeException If index is out of bounds.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Error! Task index " + (index + 1) + " is out of range.");
        }
        return tasks.remove(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The task count.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves a task by index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Error! Task index " + (index + 1) + " is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword The search term to match.
     * @return List of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}