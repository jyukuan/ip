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

    //new txt format
    public String toFileString() {
        String type = "";
        if (this instanceof Todo) {
            type = "T";
        } else if (this instanceof Deadline) {
            type = "D";
        } else if (this instanceof Event) {
            type = "E";
        }
        return type + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}

 class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
         super(description);
         this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

 class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
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
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + " | " + to;
    }
}