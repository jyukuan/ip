import java.util.Scanner;
import java.util.List;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm DeepSeek.");
        System.out.println("How can I assist you today?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Task added:\n  " + task + "\nTotal tasks: " + taskCount);
        System.out.println("____________________________________________________________");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showMarkStatus(Task task, boolean isDone) {
        System.out.println("____________________________________________________________");
        System.out.println("The task is marked as " + (isDone ? "done" : "not done") + ":");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("The task list is empty");
        } else {
            System.out.println("Current Task list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}