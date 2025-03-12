import java.io.IOException;
import java.util.List;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage system.
     * @throws DukeException If a Duke-related error occurs.
     * @throws IOException   If an I/O error occurs.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Checks whether the command is an exit command.
     *
     * @return true if it is an exit command, false otherwise.
     */
    public boolean isExit() { return false; }
}


/**
 * Represents a command that terminates the application.
 */
class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.showGoodbye();
    }
    @Override
    public boolean isExit() { return true; }
}

/**
 * Represents a command that adds a task.
 */
class AddCommand extends Command {
    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    private final Task task;
    public AddCommand(Task task) { this.task = task; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
        storage.save(tasks.getTasks());
    }
}

/**
 * Represents a command that deletes a task.
 */
class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(String indexStr) throws DukeException {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Invalid task index. Please provide a valid number.");
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task removedTask = tasks.deleteTask(index);
        ui.showTaskDeleted(removedTask, tasks.getTaskCount());
        storage.save(tasks.getTasks());
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}

class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(String indexStr, boolean isDone) throws DukeException {
        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Invalid task index. Please provide a valid number.");
        }
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = tasks.getTask(index);
        if (isDone) {
            task.markDone();
        } else {
            task.markNotDone();
        }
        ui.showMarkStatus(task, isDone);
        storage.save(tasks.getTasks());
    }
}

class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}