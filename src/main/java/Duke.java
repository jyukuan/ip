import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    //branch-A-Assertions

}