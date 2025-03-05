import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
    public boolean isExit() { return false; }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.showGoodbye();
    }
    @Override
    public boolean isExit() { return true; }
}

class AddCommand extends Command {
    private final Task task;
    public AddCommand(Task task) { this.task = task; }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getTaskCount());
        storage.save(tasks.getTasks());
    }
}

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