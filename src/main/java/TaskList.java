public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] list;
    private int listCount;

    public TaskList() {
        this.list = new Task[MAX_TASKS];
        this.listCount = 0;
    }

    public void addTask(Task task) throws DukeException {
        if (listCount >= MAX_TASKS) {
            throw new DukeException("Task list is full! Cannot add more tasks.");
        }
        list[listCount] = task;
        listCount++;
    }

    public void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Current Task List:");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public int getTaskCount() {
        return listCount;
    }
}