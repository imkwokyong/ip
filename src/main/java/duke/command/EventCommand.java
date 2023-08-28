package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!input.contains("/from")) {
            throw new DukeException(" ☹ OOPS!!! From when?");
        } else if (!input.contains("/to")) {
            throw new DukeException(" ☹ OOPS!!! To when?");
        }
        String[] tokens = input.split("/from");
        this.event = new Event(tokens[0].strip(), tokens[1].split("/to")[0].strip(),
                tokens[1].split("/to")[1].strip());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(event);
        ui.showMessage(String.format("Got it. I've added this task:\n    " +
                "%s\nNow you have %d tasks in the list.", event, taskList.getListSize()));
        storage.appendFile(event);
    }
}