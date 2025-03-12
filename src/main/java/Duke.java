import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class for running the chatbot.
 */

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    /**
     * Constructs an instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        List<Task> taskList;
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showError("Error loading tasks");
            taskList = new ArrayList<>();
        }
        tasks = new TaskList(taskList);
    }

    /**
     * Runs the chatbot program, handling user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method to start the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}