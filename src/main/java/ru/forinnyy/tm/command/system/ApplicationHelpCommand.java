package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class ApplicationHelpCommand extends AbstractSystemCommand {

    private static final String DESCRIPTION = "Display list of terminal commands.";

    private static final String NAME = "help";

    private static final String ARGUMENT = "-h";

    @Override
    public void execute() {
        System.out.println("[HELP]");
        final Collection<AbstractCommand> commands = getCommandService().getTerminalCommands();
        for (final ICommand command : commands) System.out.println(command);
    }

    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

}
