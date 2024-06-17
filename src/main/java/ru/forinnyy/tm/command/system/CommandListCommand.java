package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandListCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Display list of terminal commands.";

    @NotNull
    private static final String NAME = "commands";

    @NotNull
    private static final String ARGUMENT = "-c";

    @Override
    public void execute() {
        System.out.println("[LIST OF COMMANDS]");
        final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (@NotNull final ICommand command : commands) System.out.println(command);
    }

    @NotNull
    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

}
