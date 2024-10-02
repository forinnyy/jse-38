package ru.forinnyy.tm.command.system;

import lombok.NonNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class ApplicationHelpCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Display list of terminal commands.";

    @NonNull
    private static final String NAME = "help";

    @NonNull
    private static final String ARGUMENT = "-h";

    @Override
    public void execute() {
        System.out.println("[HELP]");
        final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (@NonNull final ICommand command : commands) System.out.println(command);
    }

    @NonNull
    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

}
