import java.util.Scanner;

public class Duke {

    //new class : task for store name and done or not
    private static class Task {
        String description;
        boolean isDone;

        Task(String name) {
            this.description = name;
            this.isDone = false;
        }

        String getStatus() {
            if (isDone) {
                return "[X]";
            } else {
                return "[ ]";
            }
        }

        void markDone() {
            this.isDone = true;
        }

        void markNotDone() {
            this.isDone = false;
        }
    }

    private static Task[] list = new Task[100];
    private static int listCount = 0;

    private static void printIt(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    private static void addToList(String input) {
        if (listCount < list.length) {
            list[listCount] = new Task(input);
            listCount++;
            printIt("added: " + input);
        } else {
            printIt("Task list is full!");
        }
    }

    private static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i + 1) + "." + list[i].getStatus() + " " + list[i].description);
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(int index) {
        if (index >= 0 && index < listCount) {
            list[index].markDone();
            printIt("Nice! I've marked this task as done:\n  " + list[index].getStatus() + " " + list[index].description);
        } else {
            printIt("Invalid!");
        }
    }

    private static void markTaskAsNotDone(int index) {
        if (index >= 0 && index < listCount) {
            list[index].markNotDone();
            printIt("OK, I've marked this task as not done yet:\n  " + list[index].getStatus() + " " + list[index].description);
        } else {
            printIt("Invalid!");
        }
    }

    private static boolean isValidNumber(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
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
            } else if (input.length() > 5 && input.substring(0, 5).equals("mark ")) {
                String numberPart = input.substring(5);
                if (isValidNumber(numberPart)) {
                    int index = Integer.parseInt(numberPart) - 1;
                    markTaskAsDone(index);
                } else {
                    printIt("Invalid task number!");
                }
            } else if (input.length() > 7 && input.substring(0, 7).equals("unmark ")) {
                String numberPart = input.substring(7);
                if (isValidNumber(numberPart)) {
                    int index = Integer.parseInt(numberPart) - 1;
                    markTaskAsNotDone(index);
                } else {
                    printIt("Invalid!");
                }
            } else {
                addToList(input);
            }
        }

        scanner.close();
    }

}


