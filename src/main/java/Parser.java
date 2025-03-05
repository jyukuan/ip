public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            if (parts.length < 2) {
                throw new DukeException("Please specify the task number to delete.");
            }
            return new DeleteCommand(parts[1].trim());
        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(parts[1].trim()));
        case "deadline":
            if (parts.length < 2) throw new DukeException("Deadline description cannot be empty!");
            String[] deadlineParts = parts[1].split("/by", 2);
            if (deadlineParts.length < 2) throw new DukeException("Invalid deadline format. Use: deadline <desc> /by <time>");
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        case "event":
            if (parts.length < 2) throw new DukeException("Event description cannot be empty!");
            String[] eventParts = parts[1].split("/from|/to", 3);
            if (eventParts.length < 3) throw new DukeException("Invalid event format. Use: event <desc> /from <time> /to <time>");
            return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
        case "mark":
            if (parts.length < 2) throw new DukeException("Please specify the task number to mark.");
            return new MarkCommand(parts[1].trim(), true);

        case "unmark":
            if (parts.length < 2) throw new DukeException("Please specify the task number to unmark.");
            return new MarkCommand(parts[1].trim(), false);

        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }
}