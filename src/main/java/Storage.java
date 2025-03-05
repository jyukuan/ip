import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<Task> load() throws IOException {
        if (!Files.exists(filePath)) return new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            try {
                tasks.add(Task.parse(line));
            } catch (DukeException e) {
                System.err.println("Error parsing task: " + line);
            }
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(filePath, lines);
    }
}