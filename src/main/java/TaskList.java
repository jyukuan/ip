import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final Path DATA_PATH = Paths.get("data", "duke.txt");

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    private void loadTasks() {
        try {
            if (!Files.exists(DATA_PATH.getParent())) {
                Files.createDirectories(DATA_PATH.getParent());
            }

            // if file do not exist, just return
            if (!Files.exists(DATA_PATH)) return;

            List<String> lines = Files.readAllLines(DATA_PATH);
            for (String line : lines) {
                try {
                    Task task = parseTask(line);
                    if (task != null) tasks.add(task);
                } catch (Exception e) {
                    System.err.println("Skip the bad data: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Loading data failed: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new IllegalArgumentException("Format error");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2].trim();

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) throw new IllegalArgumentException("due time lost");
            Deadline dl = new Deadline(description, parts[3].trim());
            dl.isDone = isDone;
            return dl;
        case "E":
            if (parts.length < 5) throw new IllegalArgumentException("time parameter lost");
            Event ev = new Event(description, parts[3].trim(), parts[4].trim());
            ev.isDone = isDone;
            return ev;
        default:
            throw new IllegalArgumentException("Unknown Task type");
        }
    }

    public void saveTasks() {
        try {
            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                content.append(task.toFileString()).append("\n");
            }
            Files.write(DATA_PATH, content.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        saveTasks();
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Undefined Index");
        }
        tasks.remove(index);
        saveTasks();
    }

    public void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Current Tasklist:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}