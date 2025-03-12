/**
 * Represents a task in the chatbot.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;
    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task as a string.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Converts the task to a standardized file format string.
     *
     * @return A string representation suitable for file storage.
     */
    public abstract String toFileString();

    /**
     * Parses a string from file storage into a Task object.
     *
     * @param line The string representation of the task from file.
     * @return The parsed Task object (Todo/Deadline/Event).
     * @throws DukeException If the input format is invalid.
     */
    public static Task parse(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new DukeException("Invalid task format in file");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            if (isDone) todo.markDone();
            return todo;
        case "D":
            if (parts.length < 4) throw new DukeException("Invalid deadline format in file");
            Deadline deadline = new Deadline(description, parts[3]);
            if (isDone) deadline.markDone();
            return deadline;
        case "E":
            if (parts.length < 5) throw new DukeException("Invalid event format in file");
            Event event = new Event(description, parts[3], parts[4]);
            if (isDone) event.markDone();
            return event;
        default:
            throw new DukeException("Unknown task type in file");
        }
    }

    public String getDescription() {
        return description;
    }
}

/**
 * Represents a Todo task with only a description.
 */
 class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Represents a Deadline task with a due time.
 */
 class Deadline extends Task {
    /** The due time of the deadline. */
    protected String by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Task description.
     * @param by Due time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * Represents an Event task with start and end times.
 */
 class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event task.
     *
     * @param description Event description.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}