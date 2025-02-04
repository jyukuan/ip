import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];
    private static int listCount = 0;

    private static String copy(String input) {
        return input;
    }

    private static void printIt(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    private static void addToList (String input) {
        if (listCount < list.length) {
            list[listCount] = input;
            listCount++;
            printIt("added: " + input);
        } else {
            printIt("Task list is full!");
        }
    }

    private static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listCount; i ++) {
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

            if (input.equalsIgnoreCase ("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase ("list")) {
                printList();
            } else {
                addToList(input);
            }

            printIt(copy(input));
        }

    }
}
