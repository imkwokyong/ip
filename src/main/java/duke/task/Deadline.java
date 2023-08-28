package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    /** Represents the deadline of the Deadline object */
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ Please enter datetime in format yyyy-MM-dd HH:mm");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringToFile() {
        return String.format("D | %s | %s", super.stringToFile(),
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
