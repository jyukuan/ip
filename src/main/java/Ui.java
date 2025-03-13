import java.util.Scanner;
import java.util.List;

/**
 * Handles user interface interactions including input and output.
 */
public class Ui {
    /** Scanner for reading user input. */
    private Scanner scanner;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next user command.
     *
     * @return The user command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }


    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm DeepSeeker.");
        System.out.println("How can I assist you today?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays confirmation message after adding a task.
     *
     * @param task The task that was added.
     * @param taskCount The new total number of tasks.
     */
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

    /**
     * Displays search results for tasks.
     *
     * @param tasks List of matching tasks found by search.
     */
    public void showMatchingTasks(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("_____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}