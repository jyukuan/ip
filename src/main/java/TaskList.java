import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        list.add(task); // ArrayList MAX_TASKS
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Error: Invalid task index!");
        }
        list.remove(index);
    }

    public void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Current Task List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public int getTaskCount() {
        return list.size(); // return ArrayList's size
    }

    public Task getTask(int index) {
        return list.get(index);
    }
}