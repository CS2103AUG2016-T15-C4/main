package jym.manager.logic.commands;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jym.manager.commons.exceptions.IllegalValueException;
import jym.manager.model.tag.Tag;
import jym.manager.model.tag.UniqueTagList;
import jym.manager.model.task.*;

/**
 * Adds a task to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: DESCRIPTION by DEADLINE (dd MM yyyy HH:mm)  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " do laundry by 07-06-2017 12:30";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String description, String address, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Description(description),
                new Location(address),
                new UniqueTagList(tagSet)
        );
    }

    public AddCommand(String description, Set<String> tags) throws IllegalValueException
    {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Description(description),
                new UniqueTagList(tagSet)
        );
    }
    public AddCommand(String description, LocalDateTime deadline, Set<String> tags) throws IllegalValueException
    {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Description(description),
                deadline,
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }

    }

}