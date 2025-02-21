public class Duke {
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                if (input.equalsIgnoreCase("bye")) {
                    ui.showGoodbye();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    taskList.printList();
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {
                    handleDelete(input);
                } else {
                    throw new DukeException("Error: Unknown command! Please enter a valid command.");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    // new logic for delete
    private void handleDelete(String input) throws DukeException {
        String indexStr = input.substring("delete".length()).trim();
        if (indexStr.isEmpty()) {
            throw new DukeException("Error: Please specify a task index to delete!");
        }
        try {
            int index = Integer.parseInt(indexStr) - 1; // input starts from 1 -> convert to 0
            Task deletedTask = taskList.getTask(index);
            taskList.deleteTask(index);
            ui.showTaskDeleted(deletedTask, taskList.getTaskCount());
        } catch (NumberFormatException e) {
            throw new DukeException("Error: Invalid task index! Please enter a number.");
        }
    }

    private void handleTodo(String input) throws DukeException {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DukeException("Error: The description of a todo cannot be empty!");
        }
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleDeadline(String input) throws DukeException {
        String rest = input.substring("deadline".length()).trim();
        if (rest.isEmpty()) {
            throw new DukeException("Error: The description and deadline of a deadline task cannot be empty!");
        }
        String[] parts = rest.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Error: Incorrect deadline format! Use: deadline <description> /by <due date>");
        }
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    private void handleEvent(String input) throws DukeException {
        String rest = input.substring("event".length()).trim();
        if (rest.isEmpty()) {
            throw new DukeException("Error: The description and time of an event cannot be empty!");
        }
        String[] parts = rest.split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new DukeException("Error: Incorrect event format! Use: event <description> /from <start time> /to <end time>");
        }
        Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}