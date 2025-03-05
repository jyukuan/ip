public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void toggleDone() {
        isDone = !isDone;
    }

    public abstract String toFileString();

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
}

class Event extends Task {
    private String from;
    private String to;

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

class Todo extends Task {
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