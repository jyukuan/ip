import java.util.Scanner;

public class Duke {

    private static abstract class Task {
        String description;
        boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        String getStatus() {
            return (isDone ? "[X]" : "[ ]");
        }

        void markDone() {
            this.isDone = true;
        }

        void markNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return getStatus() + " " + description;
        }
    }

    private static class Todo extends Task {
        Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {
        String by;

        Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        String from, to;

        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    private static Task[] list = new Task[100];
    private static int listCount = 0;

    private static void printIt(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    private static void addToList(Task task) {
        if (listCount < list.length) {
            list[listCount] = task;
            listCount++;
            printIt("Got it. I've added this task:\n  " + task + "\nNow you have " + listCount + " tasks in the list.");
        } else {
            printIt("Task list is full!");
        }
    }

    private static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm DeepSeek.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printList();
            } else if (input.startsWith("todo ")) {
                addToList(new Todo(input.substring(5)));
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                if (parts.length == 2) {
                    addToList(new Deadline(parts[0], parts[1]));
                } else {
                    printIt("Invalid deadline format!");
                }
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ", 3);
                if (parts.length == 3) {
                    addToList(new Event(parts[0], parts[1], parts[2]));
                } else {
                    printIt("Invalid event format!");
                }
            } else {
                printIt("Invalid command!");
            }
        }

        scanner.close();
    }
}
