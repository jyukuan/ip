import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
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

    /**
     * Saves tasks from input to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(filePath, lines);
    }
}